package com.xfin.service.xfin_web.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.xfin.service.xfin_web.model.HistoryStockPriceDto;
import com.xfin.service.xfin_web.model.LastTransactionDayDataDto;
import com.xfin.service.xfin_web.model.LastTransactionDayDataDto.StockData;
import com.xfin.service.xfin_web.service.XFinService;

@Service
public class XFinServiceImpl implements XFinService {

@Autowired
private RestTemplate restTemplate;

@Override
public HistoryStockPriceDto getHistoryData(String symbol, String start, String end, String interval) {
  // http://localhost:8191/stock-price/history/0005.HK?start=1741353781&end=1742353781&interval=1d
  String accessContainer = "bc-xfin-service:8091";
  String url = "http://" + accessContainer
              + "/stock-price/history/" + symbol 
              + "?start=" + start
              + "&end=" + end
              + "&interval=" + interval;
  System.out.println("Calling API: " + url);
  try {
      HistoryStockPriceDto response = restTemplate.getForObject(url, HistoryStockPriceDto.class);
      return response;
  } catch (RestClientException e) {
      e.printStackTrace();
      return null;
  }
}

@Override
public LastTransactionDayDataDto getLastTransactionDayData(String symbol) {
  // http://localhost:8191/last-transaction-day-data/BTC-USD
  String url = "http://bc-xfin-service:8091/last-transaction-day-data/" + symbol;
  try {
    LastTransactionDayDataDto response = restTemplate.getForObject(url, LastTransactionDayDataDto.class);
    @SuppressWarnings("null")
    List<StockData> data = response.getData().stream()
                  .sorted(Comparator.comparing(StockData::getRegularMarketTime))
                  .collect(Collectors.toList());
    response.setData(data);
    return response;
  } catch (Exception e) {
      System.err.println("Error fetching data for symbol: " + symbol + ". Exception: " + e.getMessage());
      return LastTransactionDayDataDto.builder()
              .lastUpdated(null)
              .data(List.of())
              .build();
  }
}


}
