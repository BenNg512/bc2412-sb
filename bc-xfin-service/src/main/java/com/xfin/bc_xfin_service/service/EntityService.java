package com.xfin.bc_xfin_service.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.xfin.bc_xfin_service.entity.StockPriceEntity;
import com.xfin.bc_xfin_service.entity.StockSymbolEntity;


public interface EntityService {
  public List<StockSymbolEntity> saveStockSymbolEntity(List<StockSymbolEntity> stockSymbolEntity);
  public StockSymbolEntity saveStockSymbolEntity(StockSymbolEntity stockSymbolEntity);
  public void saveStockSymbolsFromJson();
  public void redisSaveStockSymbols() throws JsonProcessingException;
  public List<StockSymbolEntity> redisGetAllStockSymbols();
  public StockPriceEntity saveStockPriceFromApi(String symbol);
  public void saveAllStockPriceFromApi();
}
