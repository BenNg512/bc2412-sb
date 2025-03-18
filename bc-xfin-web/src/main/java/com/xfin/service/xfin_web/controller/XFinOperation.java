package com.xfin.service.xfin_web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.xfin.service.xfin_web.model.FiveMinDataDto;

public interface XFinOperation {
  
  // http://localhost:8102/5min-data/0005.HK
  @GetMapping("/5min-data/{symbol}")
  public FiveMinDataDto getFiveMinData(@PathVariable("symbol") String symbol);

  // http://localhost:8102/stock/{symbol}
  @GetMapping(value = "/stock/{symbol}")
  public String stockPage(Model model, @PathVariable("symbol") String symbol);


}
