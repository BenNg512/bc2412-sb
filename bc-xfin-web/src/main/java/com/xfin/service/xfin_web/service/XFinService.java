package com.xfin.service.xfin_web.service;

import java.util.List;
import com.xfin.service.xfin_web.model.FiveMinDataDto;
import com.xfin.service.xfin_web.model.HistoryStockPriceDto;

public interface XFinService {
  public FiveMinDataDto getFiveMinData(String symbol);
  public FiveMinDataDto getFiveMinData(String symbol, String date);
  public List<HistoryStockPriceDto> getHistoryData(String symbol, String start, String end, String interval);
}
