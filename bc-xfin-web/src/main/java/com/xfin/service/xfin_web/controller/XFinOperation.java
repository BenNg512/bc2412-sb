package com.xfin.service.xfin_web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.xfin.service.xfin_web.model.FiveMinDataDto;
import com.xfin.service.xfin_web.model.HistoryStockPriceDto;

public interface XFinOperation {
  
  // Json
  // http://localhost:8102/5min-data/0005.HK
  @GetMapping("/5min-data/{symbol}")
  public FiveMinDataDto getFiveMinData(@PathVariable("symbol") String symbol);

  // html
  // http://localhost:8102/stock-price/daily/{symbol}
  @GetMapping(value = "/stock-price/daily/{symbol}")
  public String dailyChart(Model model, @PathVariable("symbol") String symbol);

  // html
  // http://localhost:8102/stock-price/daily/0005.HK/2025-03-18
  @GetMapping(value = "/stock-price/daily/{symbol}/{date}")
  public String dailyChart(Model model, @PathVariable("symbol") String symbol, @PathVariable("date") String date);

  // json data
  // http://localhost:8102/history/0005.HK?start=1741353781&end=1742353781&interval=1d
  @GetMapping("/history/{symbol}")
  public ResponseEntity<HistoryStockPriceDto> getHistoryData(
        @PathVariable("symbol") String symbol,
        @RequestParam String start,
        @RequestParam String end,
        @RequestParam String interval
        );

  // http://localhost:8102/history/chart/0005.HK?start=1735689600&end=2000000000&interval=1d
  @GetMapping("/history/chart/{symbol}/{start}&{end}&{interval}")
  public String getHistoryData(
        Model model,
        @PathVariable("symbol") String symbol, 
        @PathVariable("start") String start, 
        @PathVariable("end") String end,
        @PathVariable("interval") String interval
        );

}
