package com.xfin.bc_xfin_service.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager.HistoricalDataDto;
import com.xfin.bc_xfin_service.dto.FiveMinDataDto;
import com.xfin.bc_xfin_service.dto.HistoryDataDto;
import com.xfin.bc_xfin_service.entity.HistoricalStockPriceEntity;
import com.xfin.bc_xfin_service.entity.StockPriceEntity;
import com.xfin.bc_xfin_service.entity.StockSymbolEntity;


public interface EntityService {
  public void saveStockSymbolEntity(List<StockSymbolEntity> stockSymbolEntity);
  public void saveStockSymbolEntity(StockSymbolEntity stockSymbolEntity);
  public void saveStockSymbolsFromJson();
  public void redisSaveStockSymbols() throws JsonProcessingException;

  public StockPriceEntity saveStockPriceFromApi(String symbol);
  public void saveAllStockPriceFromApi();
  public String findMaxMarketDate();
  public String findMaxMarketDate(String symbol);
  public FiveMinDataDto getFiveMinData();
  public FiveMinDataDto getFiveMinData(String symbol);
  public FiveMinDataDto getFiveMinData(String symbol, String date);
  //public void redisSaveFiveMinData() throws JsonProcessingException;

  public HistoricalDataDto getHistoricalData(String symbol, String start, String end, String interval);
  public void saveHistoricalData(String symbol, String start, String end, String interval);
  public void saveAllHistoricalData (String start, String end);
  public List<HistoricalStockPriceEntity> getHistoricalDataEntity(String symbol, Integer start, Integer end, String intervalType);

  public void redisSaveHistoryData() throws JsonProcessingException;
  public HistoryDataDto redisGetHistoryData() throws JsonProcessingException;
  public HistoryDataDto redisGetHistoricalDataEntity(String symbol, Integer start, Integer end, String intervalType) throws JsonProcessingException;
}
