package com.xfin.service.xfin_web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiveMinDataDto {

    private Map<String, FiveMinData> dataMap;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FiveMinData {
        private String regularMarketTime;

        private List<FiveMinStockPriceEntity> data;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FiveMinStockPriceEntity {
        private String symbol;
        private Long regularMarketUnix;
        private Double regularMarketPrice;
        private Double regularMarketChangePercent;
        private String marketTime;
    }
}
