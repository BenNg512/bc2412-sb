package com.xfin.service.xfin_web.controller.impl;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xfin.service.xfin_web.controller.XFinOperation;
import com.xfin.service.xfin_web.model.FiveMinDataDto;
import com.xfin.service.xfin_web.model.HistoryStockPriceDto;
import com.xfin.service.xfin_web.service.impl.XFinServiceImpl;

@Controller //! not rest controller
public class XFinController implements XFinOperation {

@Autowired
private XFinServiceImpl xFinService;

private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public FiveMinDataDto getFiveMinData(String symbol) {
    return xFinService.getFiveMinData(symbol);
  }

  @Override
  public String stockPage(Model model, String symbol) {
      FiveMinDataDto data = xFinService.getFiveMinData(symbol);
      if (data == null) {
          model.addAttribute("title", "Data Not Found");
          model.addAttribute("date", "today (default day)");
          return "stock";
      }
      String date = data.getDataMap().get(symbol).getRegularMarketTime().substring(0, 11);
      model.addAttribute("date", date);
      model.addAttribute("stockData", data.getDataMap().get(symbol).getData());
      model.addAttribute("title", "Stock Price for " + symbol);
  
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

  public ResponseEntity<List<HistoryStockPriceDto>> getHistoryData(String symbol, String start, String end) {
    List<HistoryStockPriceDto> stockData = xFinService.getHistoryData(symbol, start, end);
    if (stockData.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 if no data
    }
    return ResponseEntity.ok(stockData); // 200 with the list of data
  }

  public String getHistoryData(Model model,
                              @RequestParam String symbol, 
                              @RequestParam String start, 
                              @RequestParam String end) {
    List<HistoryStockPriceDto> stockData = xFinService.getHistoryData(symbol, start, end);
    String startDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(Long.parseLong(start) * 1000));
    String endDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(Long.parseLong(end) * 1000));
    
    if (stockData == null || stockData.isEmpty()) {
        model.addAttribute("stockDataJson", "[]");
        model.addAttribute("errorMessage", "No stock data available for the given period.");
    } else {
        try {
            String stockDataJson = objectMapper.writeValueAsString(stockData);
            model.addAttribute("stockDataJson", stockDataJson);
        } catch (JsonProcessingException e) {
            model.addAttribute("stockDataJson", "[]");
            model.addAttribute("errorMessage", "Error processing stock data.");
            e.printStackTrace();
        }
    }
    // Add parameters to model
    model.addAttribute("symbol", symbol);
    model.addAttribute("start", startDate);
    model.addAttribute("end", endDate);
    return "historyChart";
  }


}
