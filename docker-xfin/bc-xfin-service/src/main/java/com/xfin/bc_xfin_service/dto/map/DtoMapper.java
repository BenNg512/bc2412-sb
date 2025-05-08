package com.xfin.bc_xfin_service.dto.map;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.xfin.bc_xfin_service.codewave.TimestampConverter;
import com.xfin.bc_xfin_service.codewave.Timezone;
import com.xfin.bc_xfin_service.dto.FiveMinDataDto;
import com.xfin.bc_xfin_service.entity.StockPriceEntity;

public class DtoMapper {
  public static FiveMinDataDto mapToFiveMinDataDto(List<StockPriceEntity> stockPriceEntities) {
        // Group entities by symbol
        Map<String, FiveMinDataDto.FiveMinData> dataMap = stockPriceEntities.stream()
            .collect(Collectors.groupingBy(
                StockPriceEntity::getSymbol, // Grouping by symbol
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    entities -> mapToFiveMinData(entities) // Transform list to FiveMinData
                )
            ));
        
        // Build and return FiveMinDataDto
        return FiveMinDataDto.builder()
            .dataMap(dataMap)
            .build();
    }

    private static FiveMinDataDto.FiveMinData mapToFiveMinData(List<StockPriceEntity> entities) {
        if (entities.isEmpty()) {
            return null;
        }

        // Extract common regularMarketTime from the first entity
        String regularMarketTime = TimestampConverter.convertTimestamp(
            entities.get(0).getRegularMarketTime(),
            Timezone.HKT,
            "yyyy-MM-dd HH:mm:ss"
        );

        // Map entities to FiveMinStockPriceEntity
        List<FiveMinDataDto.FiveMinData.FiveMinStockPriceEntity> dataList = entities.stream()
            .map(entity -> FiveMinDataDto.FiveMinData.FiveMinStockPriceEntity.builder()
                .symbol(entity.getSymbol())
                .MarketTime(entity.getRegularMarketTimeHKT()) // HKT time
                .regularMarketUnix(entity.getRegularMarketTime())
                .regularMarketPrice(entity.getRegularMarketPrice())
                .regularMarketChangePercent(entity.getRegularMarketChangePercent())
                .build()
            )
            .collect(Collectors.toList());

        // Build FiveMinData
        return FiveMinDataDto.FiveMinData.builder()
            .regularMarketTime(regularMarketTime)
            .data(dataList)
            .build();
    }
}
