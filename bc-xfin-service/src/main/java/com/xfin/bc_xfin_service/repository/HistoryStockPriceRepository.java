package com.xfin.bc_xfin_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xfin.bc_xfin_service.entity.HistoryStockPriceEntity;

public interface HistoryStockPriceRepository extends JpaRepository<HistoryStockPriceEntity, Long> {
  boolean existsBySymbolAndTimestamp(String symbol, Long timestamp);
}
