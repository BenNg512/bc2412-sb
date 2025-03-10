package com.xfin.bc_xfin_service.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.xfin.bc_xfin_service.entity.StockSymbolEntity;

public interface StockSymbolRepository extends JpaRepository<StockSymbolEntity, Long> {
  Optional<StockSymbolEntity> findBySymbol(String symbol);
}
