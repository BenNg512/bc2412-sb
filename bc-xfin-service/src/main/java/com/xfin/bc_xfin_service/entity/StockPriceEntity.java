package com.xfin.bc_xfin_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
  private long regularMarketTime;
  private double regularMarketPrice;
  private double regularMarketChangePercent;
  private double bid;
  private double ask;

}
