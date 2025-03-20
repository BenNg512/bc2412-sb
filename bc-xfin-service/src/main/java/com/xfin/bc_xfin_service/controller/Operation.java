package com.xfin.bc_xfin_service.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager.HistoricalDataDto;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager.QuoteDto;
import com.xfin.bc_xfin_service.dto.FiveMinDataDto;
import com.xfin.bc_xfin_service.entity.DailyHistoryStockPriceEntity;
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
  
  // http://localhost:8101/5min-data/0005.HK/2025-03-17
  @GetMapping("/5min-data/{symbol}/{date}")
  public FiveMinDataDto getFiveMinData(@PathVariable("symbol") String symbol, @PathVariable("date") String date);

  // http://localhost:8101/stock-price/period/0005.HK?period1=1741353781&period2=1742353781
  @GetMapping("/stock-price/period/{symbol}")
  public HistoricalDataDto getHistoricalData(@PathVariable("symbol") String symbol, 
                                        @RequestParam("start") String start, 
                                        @RequestParam("end") String end,
                                        @RequestParam("interval") String interval
    ) throws IOException;

  // http://localhost:8101/stock-price/historical/0005.HK?start=1741353781&end=1742353781
  @GetMapping("/stock-price/historical/{symbol}")
  public List<DailyHistoryStockPriceEntity> getDailyHistoricalDataEntity(@PathVariable("symbol") String symbol, 
                                                                    @RequestParam("start") Integer start, 
                                                                    @RequestParam("end") Integer end);

}
