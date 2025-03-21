package com.xfin.bc_xfin_service.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.xfin.bc_xfin_service.codewave.TimestampConverter;
import com.xfin.bc_xfin_service.codewave.Timezone;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "History_Stock_Price")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder
public class HistoricalStockPriceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length = 10, nullable = false)
  private String symbol;
  private Long timestamp;
  private Double open;
  private Double high;
  private Double low;
  private Double close;
  private Long volume;
  private Double adjClose;

  private String regularMarketTimeHKT;
  @Column(name = "id2", unique = true, nullable = false)
  private String stockPriceId;
  private String intervalType;

  @PrePersist
  @PreUpdate
  private void prePersistOrUpdate() {
    this.regularMarketTimeHKT = TimestampConverter.convertTimestamp(this.timestamp, Timezone.HKT, "yyyy-MM-dd HH:mm:ss");
    this.stockPriceId = symbol + "_" + timestamp;
  }
}
