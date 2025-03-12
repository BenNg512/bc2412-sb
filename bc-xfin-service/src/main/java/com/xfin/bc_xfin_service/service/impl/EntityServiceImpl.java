package com.xfin.bc_xfin_service.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xfin.bc_xfin_service.codewave.RedisManager;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager.QuoteDto;
import com.xfin.bc_xfin_service.entity.StockPriceEntity;
import com.xfin.bc_xfin_service.entity.StockSymbolEntity;
import com.xfin.bc_xfin_service.entity.mapper.EntityMapper;
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
RestTemplate restTemplate;

@Autowired
RedisManager redisManager;

@Autowired
YahooFinanceManager yahooFinanceManager;
// private RedisTemplate<String, String> redisTemplate;
// private ObjectMapper objectMapper;

  @Override
  public List<StockSymbolEntity> saveStockSymbolEntity(List<StockSymbolEntity> stockSymbolEntity) {
    return stockSymbolRepository.saveAll(stockSymbolEntity);
  }

  @Override
  public StockSymbolEntity saveStockSymbolEntity(StockSymbolEntity stockSymbolEntity) {
    return stockSymbolRepository.save(stockSymbolEntity);
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
  public void redisSaveStockSymbols() throws JsonProcessingException {
    List<StockSymbolEntity> entities = this.stockSymbolRepository.findAll();
    System.out.println(entities);
    String key = "stockSymbols";
    this.redisManager.set(key, entities);
  }

  // get all stock symbols from redis
  public List<StockSymbolEntity> getAllStockSymbolsFromRedis() {
    try {
      List<StockSymbolEntity> stockSymbols = redisManager.getStockSymbols();
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

  // get stock prices and save to database
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
    if (stockPriceEntity == null) {
        System.err.println("Failed to map QuoteDto.Result to StockPriceEntity for symbol: " + symbol);
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

    
}
