package com.xfin.service.xfin_web.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryStockPriceDto {
    private String lastUpdated;
    private List<HistoryStockPrice> data;
    
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HistoryStockPrice {
        private String symbol;
        private Long timestamp;
        private Double open;
        private Double high;
        private Double low;
        private Double close;
        private Long volume;
        private Double adjClose;
        private String regularMarketTimeHKT;
        private String stockPriceId;
        private String intervalType;
    }
}
