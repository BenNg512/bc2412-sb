package com.xfin.bc_xfin_service.entity.mapper;

import java.util.ArrayList;
import java.util.List;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager;
import com.xfin.bc_xfin_service.codewave.YahooFinanceManager.HistoricalDataDto;
import com.xfin.bc_xfin_service.entity.HistoricalStockPriceEntity;
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

    public static List<HistoricalStockPriceEntity> mapToHistoricalDataDto(YahooFinanceManager.HistoricalDataDto.Result result) {
        if (result == null) {
            return null;
        }
        HistoricalStockPriceEntity dto = new HistoricalStockPriceEntity();
        dto.setSymbol(result.getMeta().getSymbol());
        dto.setTimestamp(result.getTimestamp().get(0));

        return null;
    }

    public static List<HistoricalStockPriceEntity> toEntities(HistoricalDataDto dto) {
        List<HistoricalStockPriceEntity> entities = new ArrayList<>();

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
            HistoricalStockPriceEntity entity = HistoricalStockPriceEntity.builder()
                    .symbol(meta.getSymbol())
                    .timestamp(timestamps.get(i))
                    .open(safeDoubleValue(quote.getOpen(), i))
                    .high(safeDoubleValue(quote.getHigh(), i))
                    .low(safeDoubleValue(quote.getLow(), i))
                    .close(safeDoubleValue(quote.getClose(), i))
                    .volume(quote.getVolume() != null && quote.getVolume().get(i) != null ? Long.valueOf(quote.getVolume().get(i)) : null)
                    .adjClose(adjCloseValues != null && adjCloseValues.get(i) != null ? Double.valueOf(adjCloseValues.get(i)) : null)
                    .build();
        
            entities.add(entity);
        }

        return entities;
    }
    private static Double safeDoubleValue(List<Double> list, int index) {
        return (list != null && list.size() > index && list.get(index) != null) ? Double.valueOf(list.get(index)) : null;
    }
  
}
