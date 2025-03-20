package com.xfin.bc_xfin_service.service;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager.HistoricalDataDto;
import com.xfin.bc_xfin_service.dto.FiveMinDataDto;
import com.xfin.bc_xfin_service.entity.StockPriceEntity;
import com.xfin.bc_xfin_service.entity.StockSymbolEntity;


public interface EntityService {
  public void saveStockSymbolEntity(List<StockSymbolEntity> stockSymbolEntity);
  public void saveStockSymbolEntity(StockSymbolEntity stockSymbolEntity);
  public void saveStockSymbolsFromJson();
  public void redisSaveStockSymbols() throws JsonProcessingException;
  public List<StockSymbolEntity> redisGetAllStockSymbols();

  public StockPriceEntity saveStockPriceFromApi(String symbol);
  public void saveAllStockPriceFromApi();
  public void clearOneDayData();
  public String findMaxMarketDate();
  public String findMaxMarketDate(String symbol);
  public FiveMinDataDto getFiveMinData();
  public FiveMinDataDto getFiveMinData(String symbol);
  public FiveMinDataDto getFiveMinData(String symbol, String date);
  
  public HistoricalDataDto getHistoricalData(String symbol, String start, String end, String interval) throws IOException;
  public void saveDailyHistoricalDataDto(String symbol, String start, String end, String interval) throws IOException;
}
