package com.xfin.bc_xfin_service.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.xfin.bc_xfin_service.entity.HistoricalStockPriceEntity;

public interface HistoryStockPriceRepository extends JpaRepository<HistoricalStockPriceEntity, Long> {
  boolean existsBySymbolAndTimestamp(String symbol, Long timestamp);

  @Query(value = "SELECT * FROM history_stock_price s WHERE s.symbol = :symbol AND s.timestamp BETWEEN :start AND :end ORDER BY s.timestamp", nativeQuery = true)
  List<HistoricalStockPriceEntity> findData(String symbol, Long start, Long end);

  @Query(value = "SELECT * FROM history_stock_price s WHERE s.symbol = :symbol AND s.interval_type = :intervalType AND s.timestamp BETWEEN :start AND :end ORDER BY s.timestamp", nativeQuery = true)
  List<HistoricalStockPriceEntity> findDataByIntervalType(String symbol, Long start, Long end, String intervalType);
}
