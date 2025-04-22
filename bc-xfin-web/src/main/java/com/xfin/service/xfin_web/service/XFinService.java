package com.xfin.service.xfin_web.service;

import com.xfin.service.xfin_web.model.HistoryStockPriceDto;
import com.xfin.service.xfin_web.model.LastTransactionDayDataDto;

public interface XFinService {
  public HistoryStockPriceDto getHistoryData(String symbol, String start, String end, String interval);
  public LastTransactionDayDataDto getLastTransactionDayData(String symbol);
}
