package com.xfin.bc_xfin_service.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.xfin.bc_xfin_service.codewave.RedisManager;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager.QuoteDto;
import com.xfin.bc_xfin_service.controller.Operation;
import com.xfin.bc_xfin_service.entity.StockPriceEntity;
import com.xfin.bc_xfin_service.entity.StockSymbolEntity;
import com.xfin.bc_xfin_service.service.impl.EntityServiceImpl;

@RestController
public class Controller implements Operation {

  @Autowired
  private EntityServiceImpl entityService;

  @Autowired
  private YahooFinanceManager yahooFinanceManager;

  @Autowired
  private RedisManager redisManager;
  
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

  //!
  public List<StockPriceEntity> getStockPriceList(String symbol){
    return null;
  }

  @Override
  public void redisClearByKey(String key){
    this.redisManager.clearData(key);
  }

  @Override
  public StockPriceEntity saveStockPrice(String symbol) {
    return this.entityService.saveStockPriceFromApi(symbol);
  }
  
}
