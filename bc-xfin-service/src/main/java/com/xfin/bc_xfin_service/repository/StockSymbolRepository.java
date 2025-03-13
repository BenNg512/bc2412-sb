package com.xfin.bc_xfin_service.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.xfin.bc_xfin_service.entity.StockSymbolEntity;

@Repository
public interface StockSymbolRepository extends JpaRepository<StockSymbolEntity, Long> {
  Optional<StockSymbolEntity> findBySymbol(String symbol);
  List<StockSymbolEntity> findAll();
}
