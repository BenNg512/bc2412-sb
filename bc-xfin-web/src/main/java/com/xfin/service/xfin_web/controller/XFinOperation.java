package com.xfin.service.xfin_web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.xfin.service.xfin_web.model.HistoryStockPriceDto;
import com.xfin.service.xfin_web.model.LastTransactionDayDataDto;

public interface XFinOperation {

  // json data
  // http://localhost:8102/history/0005.HK?start=1741353781&end=1742353781&interval=1d
  @GetMapping("/history/{symbol}")
  public ResponseEntity<HistoryStockPriceDto> getHistoryData(
        @PathVariable("symbol") String symbol,
        @RequestParam String start,
        @RequestParam String end,
        @RequestParam String interval
        );

  // http://localhost:8102/history/chart/BTC-USD?start=1735689600&end=1742353781&interval=1d
  @GetMapping("/history/chart/{symbol}")
  public String getHistoryData(
        Model model,
        @PathVariable("symbol") String symbol, 
        @RequestParam String start, 
        @RequestParam String end,
        @RequestParam String interval
        );

  // json data
  // http://localhost:8102/last-transaction-day-data/0005.HK
  @GetMapping("/last-transaction-day-data/{symbol}")
  public ResponseEntity<LastTransactionDayDataDto> getLastTransactionDayData(@PathVariable("symbol") String symbol);

  // html
  // http://localhost:8102/latest-stock-price/0005.HK
  @GetMapping(value = "/latest-stock-price/{symbol}")
  public String lastDay(Model model, @PathVariable("symbol") String symbol);

}
