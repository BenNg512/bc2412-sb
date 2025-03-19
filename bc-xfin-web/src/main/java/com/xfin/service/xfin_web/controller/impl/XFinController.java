package com.xfin.service.xfin_web.controller.impl;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.xfin.service.xfin_web.controller.XFinOperation;
import com.xfin.service.xfin_web.model.FiveMinDataDto;
import com.xfin.service.xfin_web.service.impl.XFinServiceImpl;

@Controller //! not rest controller
public class XFinController implements XFinOperation {

@Autowired
private XFinServiceImpl xFinService;

  @Override
  public FiveMinDataDto getFiveMinData(String symbol) {
    return xFinService.getFiveMinData(symbol);
  }

  @Override
  public String stockPage(Model model, String symbol) {
    FiveMinDataDto data = xFinService.getFiveMinData(symbol);
    String date = data.getDataMap().get(symbol).getRegularMarketTime().substring(0,11);
    model.addAttribute("date", date);
    model.addAttribute("stockData", data.getDataMap().get(symbol).getData());
    model.addAttribute("title", symbol = "Stock Price for " + symbol);
    return "stock";
  }

  @Override
  public String stockPage(Model model, String symbol, String date) {
    FiveMinDataDto data = xFinService.getFiveMinData(symbol, date);
    model.addAttribute("date", date);
    
    if (data == null) {
      model.addAttribute("title", "Data Not Found");
    }
    else{
      model.addAttribute("stockData", data.getDataMap().get(symbol).getData());
      model.addAttribute("title", symbol = "Stock Price for " + symbol);
    }
    return "stock";
  }

  @ExceptionHandler(IOException.class)
  public ResponseEntity<String> handleIOException(IOException ex) {
    return ResponseEntity.status(500).body("Error fetching data: " + ex.getMessage());
  }

}
