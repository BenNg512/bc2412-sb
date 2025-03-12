package com.xfin.bc_xfin_service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xfin.bc_xfin_service.codewave.TimestampConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Stock_Price")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockPriceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String symbol;
  private Long regularMarketTime;
  private Double regularMarketPrice;
  private Double regularMarketChangePercent;
  private Double bid;
  private Double ask;
  
  @Builder.Default
  private Long currentTimeStamp = System.currentTimeMillis();
  @Builder.Default
  private String type = "5M";
  @JsonProperty("regular_market_time_UTC")
  private String regularMarketTimeUTC;

  @PrePersist
  @PreUpdate
  private void prePersistOrUpdate() {
    this.regularMarketTimeUTC = TimestampConverter.convertTimestampToUTC(this.regularMarketTime);
  }
}
