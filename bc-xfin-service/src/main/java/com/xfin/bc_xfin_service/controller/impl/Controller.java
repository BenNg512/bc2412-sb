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
  public String getLatestDataTime(){
    return this.entityService.findMaxMarketDate();
  }
  @Override
  public String getLatestDataTime(String symbol){
    return this.entityService.findMaxMarketDate(symbol);
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

  public HistoricalDataDto getDailyData(String symbol, String period1, String period2) throws IOException {
    return this.entityService.getDailyData(symbol, period1, period2);
  }
  
}
