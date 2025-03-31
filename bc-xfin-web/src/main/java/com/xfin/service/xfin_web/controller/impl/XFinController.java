package com.xfin.service.xfin_web.controller.impl;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
  public String dailyChart(Model model, String symbol) {
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
  public String dailyChart(Model model, String symbol, String date) {
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

  @Override
  public ResponseEntity<HistoryStockPriceDto> getHistoryData(String symbol, String start, String end, String interval) {
    HistoryStockPriceDto stockData = xFinService.getHistoryData(symbol, start, end, interval);

    return ResponseEntity.ok(stockData); 
  }

  @Override
  public String getHistoryData(Model model, String symbol, String start, String end, String interval) {
    HistoryStockPriceDto stockData = xFinService.getHistoryData(symbol, start, end, interval);
    String startDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(Long.parseLong(start) * 1000));
    String endDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(Long.parseLong(end) * 1000));
    model.addAttribute("symbol", symbol);
    model.addAttribute("start", startDate);
    model.addAttribute("end", endDate);
    model.addAttribute("interval", interval);
    model.addAttribute("lastUpdated", stockData.getLastUpdated().substring(0, 19));
    List<HistoryStockPriceDto.HistoryStockPrice> data = stockData.getData();

        try {
            String stockDataJson = objectMapper.writeValueAsString(data);
            model.addAttribute("stockDataJson", stockDataJson);
        } catch (JsonProcessingException e) {
            model.addAttribute("stockDataJson", "[]");
            model.addAttribute("errorMessage", "Error processing stock data.");
            e.printStackTrace();
        }
    return "historyChart";
  }


}
