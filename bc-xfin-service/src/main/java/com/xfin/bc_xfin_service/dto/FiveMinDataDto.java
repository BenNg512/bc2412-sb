package com.xfin.bc_xfin_service.dto;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
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
    private String lastUpdated;
    private List<FiveMinStockPriceEntity> data;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FiveMinStockPriceEntity {
      private String symbol;
      private String MarketTime;
      private Long regularMarketUnix;
      private Double regularMarketPrice;
      private Double regularMarketChangePercent;
    }
  }
}
