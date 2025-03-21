package com.bootcamp.demo.demo_swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.demo.demo_swagger.entity.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, String> {
  
}
