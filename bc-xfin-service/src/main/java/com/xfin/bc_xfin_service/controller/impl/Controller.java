package com.xfin.bc_xfin_service.controller.impl;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager.HistoricalDataDto;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager.QuoteDto;
import com.xfin.bc_xfin_service.controller.Operation;
import com.xfin.bc_xfin_service.dto.FiveMinDataDto;
import com.xfin.bc_xfin_service.entity.HistoricalStockPriceEntity;
import com.xfin.bc_xfin_service.entity.StockPriceEntity;
import com.xfin.bc_xfin_service.entity.StockSymbolEntity;
import com.xfin.bc_xfin_service.service.impl.EntityServiceImpl;

@RestController
public class Controller implements Operation {

  @Autowired
  private EntityServiceImpl entityService;

  YahooFinanceManager yahooFinanceManager = new YahooFinanceManager();

  @Override
  public void saveSymbolEntities(List<StockSymbolEntity> stockSymbolEntity) {
    this.entityService.saveStockSymbolEntity(stockSymbolEntity);
  }
  
  @Override
  public void saveSymbolEntity(StockSymbolEntity stockSymbolEntity) {
    this.entityService.saveStockSymbolEntity(stockSymbolEntity);
  }

  @Override
  public QuoteDto getQuote(String symbol) {
      return this.yahooFinanceManager.getQuote(symbol);
  }
  
  @Override
  public List<StockSymbolEntity> redisGetAllStockSymbols() {
    return this.entityService.redisGetAllStockSymbols();
  }

  @Override
  public StockPriceEntity saveStockPrice(String symbol) {
    return this.entityService.saveStockPriceFromApi(symbol);
  }

  @Override
  public FiveMinDataDto getFiveMinData() {
    return this.entityService.getFiveMinData();
  }
  @Override
  public FiveMinDataDto getFiveMinData(String symbol) {
    return this.entityService.getFiveMinData(symbol);
  }

  @Override
  public FiveMinDataDto getFiveMinData(String symbol, String date) {
    return this.entityService.getFiveMinData(symbol, date);
  }

  @Override
  public HistoricalDataDto getHistoricalData(String symbol, String start, String end, String interval) throws IOException {
    return this.entityService.getHistoricalData(symbol, start, end, interval);
  }

  @Override
  public List<HistoricalStockPriceEntity> getDailyHistoricalDataEntity(String symbol, Integer start, Integer end, String interval) {
    return this.entityService.getDailyHistoricalDataEntity(symbol, start, end, interval);
  }
  
}
