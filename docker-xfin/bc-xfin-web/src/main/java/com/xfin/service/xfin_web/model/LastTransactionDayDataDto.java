package com.xfin.service.xfin_web.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class LastTransactionDayDataDto {
  private String lastUpdated;
  private List<StockData> data;

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class StockData {
    private String symbol;
    private Long regularMarketTime;
    private Double regularMarketPrice;
    private Double regularMarketChangePercent;
    @JsonProperty("regular_market_time_HKT")
    private String regularMarketTimeHKT;
  }
  
}
