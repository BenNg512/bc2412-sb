package com.xfin.bc_xfin_service.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xfin.bc_xfin_service.codewave.RedisManager;
import com.xfin.bc_xfin_service.codewave.TimestampConverter;
import com.xfin.bc_xfin_service.codewave.Timezone;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager.HistoricalDataDto;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager.QuoteDto;
import com.xfin.bc_xfin_service.dto.FiveMinDataDto;
import com.xfin.bc_xfin_service.dto.HistoryDataDto;
import com.xfin.bc_xfin_service.dto.map.DtoMapper;
import com.xfin.bc_xfin_service.entity.HistoricalStockPriceEntity;
import com.xfin.bc_xfin_service.entity.StockPriceEntity;
import com.xfin.bc_xfin_service.entity.StockSymbolEntity;
import com.xfin.bc_xfin_service.entity.mapper.EntityMapper;
import com.xfin.bc_xfin_service.repository.HistoryStockPriceRepository;
import com.xfin.bc_xfin_service.repository.StockPriceRepository;
import com.xfin.bc_xfin_service.repository.StockSymbolRepository;
import com.xfin.bc_xfin_service.service.EntityService;

@Service
public class EntityServiceImpl implements EntityService {

@Autowired
private StockSymbolRepository stockSymbolRepository;
@Autowired
private StockPriceRepository stockPriceRepository;
@Autowired
private HistoryStockPriceRepository historyStockPriceRepository;
@Autowired
private RedisManager redisManager;

YahooFinanceManager yahooFinanceManager = new YahooFinanceManager();

  @Override
  public void saveStockSymbolEntity(List<StockSymbolEntity> stockSymbolEntity) {
    this.stockSymbolRepository.saveAll(stockSymbolEntity);
  }

  @Override
  public void saveStockSymbolEntity(StockSymbolEntity stockSymbolEntity) {
    this.stockSymbolRepository.save(stockSymbolEntity);
  }

  // save default StockSymbol to database
  @Override
  public void saveStockSymbolsFromJson() {
    try {
        // Load JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/StockSymbol.json");

        // Map JSON to List of StockSymbolEntity
        List<StockSymbolEntity> stockSymbols = objectMapper.readValue(inputStream, new TypeReference<List<StockSymbolEntity>>() {});

        // Check if each symbol exists before saving
        for (StockSymbolEntity stockSymbol : stockSymbols) {
            Optional<StockSymbolEntity> existingSymbol = stockSymbolRepository.findBySymbol(stockSymbol.getSymbol());
            if (existingSymbol.isEmpty()) {
                stockSymbolRepository.save(stockSymbol);
                System.out.println("Saved stock symbol: " + stockSymbol.getSymbol());
            } else {
                System.out.println("Skipped existing stock symbol: " + stockSymbol.getSymbol());
            }
        }
        System.out.println("Stock symbols processed successfully!");
    } catch (Exception e) {
        e.printStackTrace();
    }
  }

  // save default StockSymbol to redis
  @Override
  public void redisSaveStockSymbols() throws JsonProcessingException {
    List<StockSymbolEntity> entities = this.stockSymbolRepository.findAll();
    System.out.println(entities);
    String key = "stockSymbols";
    this.redisManager.set(key, entities);
  }

  // get stock prices and save to database & redis
  @Override
  public StockPriceEntity saveStockPriceFromApi(String symbol) {
    QuoteDto quoteDto = this.yahooFinanceManager.getQuote(symbol);
    if (quoteDto == null || quoteDto.getQuoteResponse() == null) {
    //  System.err.println("QuoteDto or QuoteResponse is null for symbol: " + symbol);
        return null;
    }
    List<YahooFinanceManager.QuoteDto.Result> results = quoteDto.getQuoteResponse().getResult();
    if (results == null || results.isEmpty()) {
    //  System.err.println("No results found for symbol: " + symbol);
        return null;
    }
    // map 
    StockPriceEntity stockPriceEntity = EntityMapper.mapToStockPriceEntity(results.get(0));
    if (stockPriceEntity == null) {
    // System.err.println("Failed to map QuoteDto.Result to StockPriceEntity for symbol: " + symbol);
      return null;
    }
    try {
        stockPriceRepository.save(stockPriceEntity);
        System.out.println("Stock price for symbol " + symbol + " saved successfully.");
        return stockPriceEntity;
    } catch (Exception e) {
        System.err.println("Error saving stock price for symbol: " + symbol);
        e.printStackTrace();
        return null;
    }
  }

  // get api and save all stock price to database
  @Override
  public void saveAllStockPriceFromApi(){
    List<String> symbols = this.stockSymbolRepository.findAll()
                                                  .stream()
                                                  .map(StockSymbolEntity::getSymbol)
                                                  .collect(Collectors.toList());
    for (String string : symbols) {
      this.saveStockPriceFromApi(string);
    }
  }

  @Override
  public String findMaxMarketDate(){
    Long time = this.stockPriceRepository.findMaxRegularMarketTime();
    String date = TimestampConverter.convertTimestamp(time, Timezone.HKT, "yyyy-MM-dd");
    return date;
  }
  @Override
  public String findMaxMarketDate(String symbol){
    Long time = this.stockPriceRepository.findMaxRegularMarketTime();
    String date = TimestampConverter.convertTimestamp(time, Timezone.HKT, "yyyy-MM-dd");
    return date;
  }

  @Override
  public FiveMinDataDto getFiveMinData() {
    String date = findMaxMarketDate();
    //List<StockPriceEntity> stockPriceEntities = this.stockPriceRepository.findAll();
    List<StockPriceEntity> stockPriceEntities = 
        this.stockPriceRepository.findByRegularMarketTime(date);
    if (stockPriceEntities != null && !stockPriceEntities.isEmpty()) {
      return DtoMapper.mapToFiveMinDataDto(stockPriceEntities);
    }
    return null;
  }

  @Override
  public FiveMinDataDto getFiveMinData(String symbol) {
    String date = findMaxMarketDate();

    List<StockPriceEntity> stockPriceEntities = 
        this.stockPriceRepository.findByRegularMarketTime(date, symbol);
    if (stockPriceEntities != null && !stockPriceEntities.isEmpty()) {
      return DtoMapper.mapToFiveMinDataDto(stockPriceEntities);
    }
    return null;
  }

  @Override
  public FiveMinDataDto getFiveMinData(String symbol, String date) {
    List<StockPriceEntity> stockPriceEntities = 
        this.stockPriceRepository.findByRegularMarketTime(date, symbol);
    if (stockPriceEntities != null && !stockPriceEntities.isEmpty()) {
      return DtoMapper.mapToFiveMinDataDto(stockPriceEntities);
    }
    return null;
  }

  @Override
  public HistoricalDataDto getHistoricalData(String symbol, String start, String end, String interval) {
    return this.yahooFinanceManager.getHistoricalData(symbol, start, end, interval);
  }

  @Override
  public void saveHistoricalData(String symbol, String start, String end, String interval) {
    try {
        HistoricalDataDto data = this.yahooFinanceManager.getHistoricalData(symbol, start, end, interval);
        if (data == null || data.getChart() == null || data.getChart().getResult() == null) {
            throw new IOException("No data returned from Yahoo Finance API for symbol: " + symbol);
        }
        List<HistoricalStockPriceEntity> entities = EntityMapper.toEntities(data);

        List<HistoricalStockPriceEntity> newEntities = entities.stream()
            .filter(entity -> !historyStockPriceRepository.existsBySymbolAndTimestampAndIntervalType(
                entity.getSymbol(), entity.getTimestamp(), entity.getIntervalType()
            ))
            .peek(entity -> entity.setIntervalType(interval)) // Set intervalType during the stream processing
            .toList();
        if (!newEntities.isEmpty()) {
            historyStockPriceRepository.saveAll(newEntities);
            System.out.println("Successfully saved " + newEntities.size() + " new records for symbol: " + symbol);
        } else {
            System.out.println("No new data to save for symbol: " + symbol + " and interval: " + interval);
        }
    } catch (IOException e) {
        System.err.println("Error fetching historical data for symbol: " + symbol + " - " + e.getMessage());
    } catch (Exception e) {
        System.err.println("An unexpected error occurred while saving historical data: " + e.getMessage());
        e.printStackTrace();
    }
}

  @Override
  public void saveAllHistoricalData(String start, String end){
    //this.historyStockPriceRepository.deleteAll();

    if (Long.valueOf(end) > LocalDate.now()
        .atStartOfDay(ZoneId.of(Timezone.HKT.value))
        .toEpochSecond()){
      end = LocalDate.now()
        .atStartOfDay(ZoneId.of(Timezone.HKT.value))
        .toEpochSecond()+"";
    }
    
    List<String> symbols = this.stockSymbolRepository.findAll()
        .stream()
        .map(StockSymbolEntity::getSymbol)
        .collect(Collectors.toList());
    for (String symbol : symbols) {
        this.saveHistoricalData(symbol, start, end, "3mo");
        this.saveHistoricalData(symbol, start, end, "1mo");
        this.saveHistoricalData(symbol, start, end, "5d");
        this.saveHistoricalData(symbol, start, end, "1d");
    }
  }

  @Override
  public List<HistoricalStockPriceEntity> getHistoricalDataEntity(String symbol, Integer start, Integer end, String intervalType){
    if (end > LocalDate.now()
        .atStartOfDay(ZoneId.of(Timezone.HKT.value))
        .toEpochSecond()){
      end = (int) LocalDate.now()
        .atStartOfDay(ZoneId.of(Timezone.HKT.value))
        .toEpochSecond();
    }
    
    return this.historyStockPriceRepository.findDataByIntervalType(symbol, Long.valueOf(start), Long.valueOf(end), intervalType);
  }

  @Override
  public void redisSaveHistoryData() throws JsonProcessingException {
    List<HistoricalStockPriceEntity> entities = this.historyStockPriceRepository.findAll();
    new HistoryDataDto();
    HistoryDataDto historyDataDto = HistoryDataDto.builder()
        .lastUpdated(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString())
        .data(entities)
        .build();
    this.redisManager.clearData("HistoryStockPrice");
    this.redisManager.set("HistoryStockPrice", historyDataDto);
    System.out.println("Data successfully saved to Redis");
  }

  @Override
  public HistoryDataDto redisGetHistoryData() throws JsonProcessingException {
    return this.redisManager.get2("HistoryStockPrice", new TypeReference<HistoryDataDto>() {});
  }


  @Override
  public HistoryDataDto redisGetHistoricalDataEntity(String symbol, Integer start, Integer end, String intervalType) throws JsonProcessingException{
    HistoryDataDto entities = this.redisGetHistoryData();
    List<HistoricalStockPriceEntity> data = entities.getData();
    if (data != null && !data.isEmpty()) {
      List<HistoricalStockPriceEntity> data2 = data.stream()
          .filter(entity -> entity.getSymbol().equals(symbol))
          .filter(entity -> entity.getTimestamp() >= start)
          .filter(entity -> entity.getTimestamp() <= end)
          .filter(entity -> entity.getIntervalType().equals(intervalType))
          .sorted(Comparator.comparing(HistoricalStockPriceEntity::getTimestamp))
          .collect(Collectors.toList());
      return HistoryDataDto.builder()
        .lastUpdated(entities.getLastUpdated())
        .data(data2)
        .build();
    }
    return null;
  }

}