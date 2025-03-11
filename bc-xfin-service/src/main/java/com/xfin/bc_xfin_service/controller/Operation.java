package com.xfin.bc_xfin_service.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager.QuoteDto;
import com.xfin.bc_xfin_service.entity.StockPriceEntity;
import com.xfin.bc_xfin_service.entity.StockSymbolEntity;

public interface Operation {
  // http://localhost:8101/stock/symbols
  @PostMapping("/stock/symbols")
  public List<StockSymbolEntity> savSymbolEntities(@RequestBody List<StockSymbolEntity> stockSymbolEntity);

  // http://localhost:8101/stock/symbol
  @PostMapping("/stock/symbol")
  public StockSymbolEntity savSymbolEntity(@RequestBody StockSymbolEntity stockSymbolEntity);

  // http://localhost:8101/api?symbol=0005.HK
  @GetMapping("/api")
  public QuoteDto getQuote(@RequestParam String symbol);

  // http://localhost:8101/stock/symbols
  @GetMapping("/stock/symbols")
  public List<StockSymbolEntity> getAllStockSymbolsFromRedis();

  // http://localhost:8101/stock/symbol?symbol=0005.HK
  @GetMapping("/stock/symbol")
  public StockPriceEntity getStockPrice(@RequestParam String symbol);

}
