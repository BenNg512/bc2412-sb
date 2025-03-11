package com.xfin.bc_xfin_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xfin.bc_xfin_service.entity.StockPriceEntity;

public interface StockPriceRepository extends JpaRepository<StockPriceEntity, Long> {

}
