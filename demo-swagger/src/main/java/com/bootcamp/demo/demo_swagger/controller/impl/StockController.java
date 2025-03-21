package com.bootcamp.demo.demo_swagger.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_swagger.controller.StockOperation;
import com.bootcamp.demo.demo_swagger.dto.StockDTO;
import com.bootcamp.demo.demo_swagger.dto.mapper.DTOMapper;
import com.bootcamp.demo.demo_swagger.entity.StockEntity;
import com.bootcamp.demo.demo_swagger.entity.mapper.EntityMapper;
import com.bootcamp.demo.demo_swagger.service.StockService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class StockController implements StockOperation {
  @Autowired
  private StockService stockService;
  @Autowired
  private DTOMapper dtoMapper; // address
  @Autowired
  private EntityMapper entityMapper; // address

  @Override
  public StockDTO getStock(String symbol) {
    StockEntity stockEntity = this.stockService.findStock(symbol).orElse(null);
    return this.dtoMapper.map(stockEntity);
  }

  @Override
  public StockDTO createStock(StockDTO stockDTO) {
    StockEntity stockEntity = this.entityMapper.map(stockDTO);
    stockEntity = this.stockService.saveStock(stockEntity);
    return this.dtoMapper.map(stockEntity);
  }

  @Override
  public void deleteStock(String symbol) {
    this.stockService.deleteStock(symbol);
  }

  @Override
  public StockDTO updateStock(String symbol, StockDTO stockDTO) {
    StockEntity stockEntity = this.entityMapper.map(stockDTO);
    stockEntity = this.stockService.updateStock(symbol, stockEntity);
    return this.dtoMapper.map(stockEntity);
  }

  @Override
  public StockDTO patchStockDescription(String symbol,
      String stockDescription) {
    StockEntity stockEntity =
        this.stockService.patchStockDescription(symbol, stockDescription);
    return this.dtoMapper.map(stockEntity);
  }
}
