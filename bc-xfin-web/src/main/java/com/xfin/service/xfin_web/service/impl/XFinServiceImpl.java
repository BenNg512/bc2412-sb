package com.xfin.service.xfin_web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.xfin.service.xfin_web.model.FiveMinDataDto;
import com.xfin.service.xfin_web.model.HistoryStockPriceDto;
import com.xfin.service.xfin_web.service.XFinService;

@Service
public class XFinServiceImpl implements XFinService {

@Autowired
private RestTemplate restTemplate;

@Override
public FiveMinDataDto getFiveMinData(String symbol) {
  String url = "http://localhost:8101/5min-data/" + symbol;
  return restTemplate.getForObject(url, FiveMinDataDto.class);
}
@Override
public FiveMinDataDto getFiveMinData(String symbol, String date) {
  String url = "http://localhost:8101/5min-data/" + symbol + "/" + date;
  return restTemplate.getForObject(url, FiveMinDataDto.class);
}

@Override
public List<HistoryStockPriceDto> getHistoryData(String symbol, String start, String end, String interval) {
  // http://localhost:8101/stock-price/history/0005.HK?start=1741353781&end=1742353781&interval=1d
  String url = "http://localhost:8101/stock-price/history/" 
              + symbol 
              + "?start=" + start 
              + "&end=" + end
              + "&interval=" + interval;
  System.out.println("Calling API: " + url);
  try {
      HistoryStockPriceDto[] responseArray = restTemplate.getForObject(url, HistoryStockPriceDto[].class);
      List<HistoryStockPriceDto> result = Arrays.asList(responseArray);
      System.out.println("API returned " + result.size() + " records");
      return result;
  } catch (RestClientException e) {
      System.err.println("Error fetching data for symbol: " + symbol + " - " + e.getMessage());
      e.printStackTrace();
      return new ArrayList<>();
  }
}




}
