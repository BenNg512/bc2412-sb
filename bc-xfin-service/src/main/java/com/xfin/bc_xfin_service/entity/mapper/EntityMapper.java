package com.xfin.bc_xfin_service.entity.mapper;

import java.util.ArrayList;
import java.util.List;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager.HistoricalDataDto;
import com.xfin.bc_xfin_service.entity.HistoryStockPriceEntity;
import com.xfin.bc_xfin_service.entity.OneDayStockPriceEntity;
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

    public static OneDayStockPriceEntity mapToOneDayStockPriceEntity(YahooFinanceManager.QuoteDto.Result result) {
        if (result == null) {
            return null;
        }
        OneDayStockPriceEntity entity = new OneDayStockPriceEntity();
        entity.setSymbol(result.getSymbol());
        entity.setRegularMarketTime(result.getRegularMarketTime());
        entity.setRegularMarketPrice(result.getRegularMarketPrice());
        entity.setRegularMarketChangePercent(result.getRegularMarketChangePercent());
        entity.setBid(result.getBid());
        entity.setAsk(result.getAsk());
        return entity;
    }

    public static List<HistoryStockPriceEntity> mapToHistoricalDataDto(YahooFinanceManager.HistoricalDataDto.Result result) {
        if (result == null) {
            return null;
        }
        HistoryStockPriceEntity dto = new HistoryStockPriceEntity();
        dto.setSymbol(result.getMeta().getSymbol());
        dto.setTimestamp(result.getTimestamp().get(0));

        return null;
    }

    public static List<HistoryStockPriceEntity> toEntities(HistoricalDataDto dto) {
        List<HistoryStockPriceEntity> entities = new ArrayList<>();

        // Check if DTO or its nested structures are null
        if (dto == null || dto.getChart() == null || dto.getChart().getResult() == null || dto.getChart().getResult().isEmpty()) {
            return entities;
        }

        // Get the first result (Yahoo Finance typically returns one result)
        HistoricalDataDto.Result result = dto.getChart().getResult().get(0);
        HistoricalDataDto.Meta meta = result.getMeta();
        List<Long> timestamps = result.getTimestamp();
        HistoricalDataDto.Indicators indicators = result.getIndicators();

        // Check if required data is present
        if (timestamps == null || indicators == null || indicators.getQuote() == null || indicators.getQuote().isEmpty()) {
            return entities;
        }

        HistoricalDataDto.Quote quote = indicators.getQuote().get(0);
        List<HistoricalDataDto.AdjClose> adjCloses = indicators.getAdjclose();
        List<Double> adjCloseValues = (adjCloses != null && !adjCloses.isEmpty()) ? adjCloses.get(0).getAdjclose() : null;

        // Iterate over timestamps and map to entities
        for (int i = 0; i < timestamps.size(); i++) {

            // Create entity
            HistoryStockPriceEntity entity = HistoryStockPriceEntity.builder()
                    .symbol(meta.getSymbol())
                    .timestamp(timestamps.get(i))
                    .open(Double.valueOf(quote.getOpen().get(i)))
                    .high(Double.valueOf(quote.getHigh().get(i)))
                    .low(Double.valueOf(quote.getLow().get(i)))
                    .close(Double.valueOf(quote.getClose().get(i)))
                    .volume(Long.valueOf(quote.getVolume().get(i)))
                    .adjClose(adjCloseValues != null ? Double.valueOf(adjCloseValues.get(i)) : null)
                    .build();

            entities.add(entity);
        }

        return entities;
    }
  
}
