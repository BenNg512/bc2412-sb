package com.xfin.bc_xfin_service.service.impl;

import java.io.IOException;
import java.io.InputStream;
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
import com.xfin.bc_xfin_service.dto.map.DtoMapper;
import com.xfin.bc_xfin_service.entity.HistoricalStockPriceEntity;
import com.xfin.bc_xfin_service.entity.OneDayStockPriceEntity;
import com.xfin.bc_xfin_service.entity.StockPriceEntity;
import com.xfin.bc_xfin_service.entity.StockSymbolEntity;
import com.xfin.bc_xfin_service.entity.mapper.EntityMapper;
import com.xfin.bc_xfin_service.repository.HistoryStockPriceRepository;
import com.xfin.bc_xfin_service.repository.OneDayStockPriceRepository;
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
private OneDayStockPriceRepository oneDayStockPriceRepository;
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

  // get all stock symbols from redis
  @Override
  public List<StockSymbolEntity> redisGetAllStockSymbols() {
    try {
      //List<StockSymbolEntity> stockSymbols = this.redisManager.getStockSymbols();
      List<StockSymbolEntity> stockSymbols = this.redisManager.getStockSymbols();
            if (stockSymbols != null) {
                System.out.println("Stock symbols retrieved from Redis!");
            } else {
                System.out.println("No stock symbols found in Redis.");
            }
            return stockSymbols;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

  // get stock prices and save to database & redis
  @Override
  public StockPriceEntity saveStockPriceFromApi(String symbol) {
    QuoteDto quoteDto = this.yahooFinanceManager.getQuote(symbol);
    if (quoteDto == null || quoteDto.getQuoteResponse() == null) {
        System.err.println("QuoteDto or QuoteResponse is null for symbol: " + symbol);
        return null;
    }
    List<YahooFinanceManager.QuoteDto.Result> results = quoteDto.getQuoteResponse().getResult();
    if (results == null || results.isEmpty()) {
        System.err.println("No results found for symbol: " + symbol);
        return null;
    }
    // map 
    StockPriceEntity stockPriceEntity = EntityMapper.mapToStockPriceEntity(results.get(0));
    OneDayStockPriceEntity stockPriceEntity2 = EntityMapper.mapToOneDayStockPriceEntity(results.get(0));
    if (stockPriceEntity == null || stockPriceEntity2 == null) {
      System.err.println("Failed to map QuoteDto.Result to StockPriceEntity for symbol: " + symbol);
      return null;
    }
    try {
        oneDayStockPriceRepository.save(stockPriceEntity2);
        stockPriceRepository.save(stockPriceEntity);
        redisUpdateStockPrice(symbol, stockPriceEntity);
        System.out.println("Stock price for symbol " + symbol + " saved successfully.");
        return stockPriceEntity;
    } catch (Exception e) {
        System.err.println("Error saving stock price for symbol: " + symbol);
        e.printStackTrace();
        return null;
    }
  }

  // update redis: if symbol exists, update; if not, add
  public void redisUpdateStockPrice(String symbol, StockPriceEntity newStockPriceEntity) throws Exception {
      try {
              @SuppressWarnings("unchecked")
              List<StockPriceEntity> existingStockPriceEntityList = (List<StockPriceEntity>) this.redisManager.get(symbol, StockPriceEntity.class);
              if (existingStockPriceEntityList != null) {
                  existingStockPriceEntityList.add(newStockPriceEntity);
                  this.redisManager.set(symbol, existingStockPriceEntityList);
                  System.out.println("Successfully updated " + symbol);
                  return;
              }
              else if (existingStockPriceEntityList == null){
              List<StockPriceEntity> stockPriceEntityList = List.of(newStockPriceEntity);
              this.redisManager.set(symbol, stockPriceEntityList);
              }
              System.out.println("Successfully added " + symbol);
      } catch (Exception e) {
          System.err.println("Failed to update " + symbol + ": " + e.getMessage());
          throw e;
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
  public void clearOneDayData(){
    this.oneDayStockPriceRepository.deleteAll();
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
            throw new IOException("No data returned from Yahoo Finance API");
        }
        List<HistoricalStockPriceEntity> entities = EntityMapper.toEntities(data);
        // Filter out entities that already exist in the database
        List<HistoricalStockPriceEntity> newEntities = entities.stream()
            .filter(entity -> !historyStockPriceRepository.existsBySymbolAndTimestamp(entity.getSymbol(), entity.getTimestamp()))
            .toList();
        if (!newEntities.isEmpty()) {
            for (HistoricalStockPriceEntity entity : newEntities) {
                entity.setIntervalType(interval);
            }
            historyStockPriceRepository.saveAll(newEntities);
        }
    } catch (IOException e) {
    }
  }

  public void saveAllHistoricalData(String start, String end){
    List<String> symbols = this.stockSymbolRepository.findAll()
        .stream()
        .map(StockSymbolEntity::getSymbol)
        .collect(Collectors.toList());
    for (String symbol : symbols) {
        this.saveHistoricalData(symbol, start, end, "5d");
        this.saveHistoricalData(symbol, start, end, "1d");
    }
  }

  @Override
  public List<HistoricalStockPriceEntity> getDailyHistoricalDataEntity(String symbol, Integer start, Integer end){
    return this.historyStockPriceRepository.findData(symbol, Long.valueOf(start), Long.valueOf(end));
  }
  @Override
  public List<HistoricalStockPriceEntity> getDailyHistoricalDataEntity(String symbol, Integer start, Integer end, String intervalType){
    return this.historyStockPriceRepository.findDataByIntervalType(symbol, Long.valueOf(start), Long.valueOf(end), intervalType);
  }


}
