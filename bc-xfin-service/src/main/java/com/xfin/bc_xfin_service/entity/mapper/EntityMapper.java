package com.xfin.bc_xfin_service.entity.mapper;

import com.xfin.bc_xfin_service.codewave.YahooFinanceManager;
import com.xfin.bc_xfin_service.entity.StockPriceEntity;

public class EntityMapper {
    public static StockPriceEntity mapToStockPriceEntity(YahooFinanceManager.QuoteDto.Result result) {
        if (result == null) {
            return null;
        }

        StockPriceEntity entity = new StockPriceEntity();
        entity.setSymbol(result.getSymbol());
        entity.setRegularMarketTime(result.getRegularMarketTime());
        entity.setRegularMarketPrice(result.getRegularMarketPrice());
        entity.setRegularMarketChangePercent(result.getRegularMarketChangePercent());
        entity.setBid(result.getBid());
        entity.setAsk(result.getAsk());

        return entity;
    }
  
}
