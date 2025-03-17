package com.xfin.bc_xfin_service.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager.QuoteDto;
import com.xfin.bc_xfin_service.dto.FiveMinDataDto;
import com.xfin.bc_xfin_service.entity.StockPriceEntity;
import com.xfin.bc_xfin_service.entity.StockSymbolEntity;

public interface Operation {
  // http://localhost:8101/stock/symbols
  @PostMapping("/stock/symbols")
  public void saveSymbolEntities(@RequestBody List<StockSymbolEntity> stockSymbolEntity);

  // http://localhost:8101/stock/symbol
  @PostMapping("/stock/symbol")
  public void saveSymbolEntity(@RequestBody StockSymbolEntity stockSymbolEntity);

  // http://localhost:8101/api?symbol=0005.HK
  @GetMapping("/api")
  public QuoteDto getQuote(@RequestParam String symbol);

  // http://localhost:8101/stock/symbols
  @GetMapping("/stock/symbols")
  public List<StockSymbolEntity> redisGetAllStockSymbols();

  // http://localhost:8101/stockprice?symbol=0005.HK
  @GetMapping("/stockprice")
  public List<StockPriceEntity> getStockPriceList(@RequestParam String symbol);

  // http://localhost:8101/stockprice/clear?key=0005.HK
  @GetMapping("/stockprice/clear")
  public void redisClearByKey(@RequestParam String key);

  // http://localhost:8101/stock/symbol?symbol=0005.HK
  @GetMapping("/stock/symbol")
  public StockPriceEntity saveStockPrice(@RequestParam String symbol);

  // http://localhost:8101/market-date
  @GetMapping("/market-date")
  public String getLatestDataTime();

  // http://localhost:8101/market-date/0005.HK
  @GetMapping("/market-date/{symbol}")
  public String getLatestDataTime(@PathVariable("symbol") String symbol);

  // http://localhost:8101/5min-data
  @GetMapping("/5min-data")
  public FiveMinDataDto getFiveMinData();

  // http://localhost:8101/5min-data/0005.HK
  @GetMapping("/5min-data/{symbol}")
  public FiveMinDataDto getFiveMinData(@PathVariable("symbol") String symbol);
  
}
