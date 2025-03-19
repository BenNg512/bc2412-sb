package com.xfin.bc_xfin_service.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.xfin.bc_xfin_service.entity.StockPriceEntity;

public interface StockPriceRepository extends JpaRepository<StockPriceEntity, Long> {
  @Query(value = "SELECT MAX(s.regular_market_time) FROM stock_price s", nativeQuery = true)
  Long findMaxRegularMarketTime();

  @Query(value = "SELECT MAX(s.regular_market_time) FROM stock_price s WHERE s.symbol = :symbol", nativeQuery = true)
  Long findMaxRegularMarketTime(String symbol);

  @Query(value = "SELECT * FROM stock_price s WHERE CAST(s.regular_market_timehkt AS DATE) = CAST(:time AS DATE)", nativeQuery = true)
  List<StockPriceEntity> findByRegularMarketTime(@Param("time") String time);

  @Query(value = "SELECT * FROM stock_price s WHERE CAST(s.regular_market_timehkt AS DATE) = CAST(:time AS DATE) AND s.symbol = :symbol ORDER BY s.id", nativeQuery = true)
  List<StockPriceEntity> findByRegularMarketTime(@Param("time") String time, @Param("symbol") String symbol);
  
}
