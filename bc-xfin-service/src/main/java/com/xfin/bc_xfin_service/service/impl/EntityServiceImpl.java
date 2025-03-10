package com.xfin.bc_xfin_service.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xfin.bc_xfin_service.entity.StockSymbolEntity;
import com.xfin.bc_xfin_service.repository.StockSymbolRepository;
import com.xfin.bc_xfin_service.service.EntityService;

@Service
public class EntityServiceImpl implements EntityService {

@Autowired
  private StockSymbolRepository stockSymbolRepository;
  
  @Override
  public List<StockSymbolEntity> saveStockSymbolEntity(List<StockSymbolEntity> stockSymbolEntity) {
    return stockSymbolRepository.saveAll(stockSymbolEntity);
  }

  @Override
  public StockSymbolEntity saveStockSymbolEntity(StockSymbolEntity stockSymbolEntity) {
    return stockSymbolRepository.save(stockSymbolEntity);
  }

  // save default StockSymbol to database
  @Override
  public void saveStockSymbolsFromJson() {
    try {
        // Load JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/StockSymbol.json");

        // Map JSON to List of StockSymbolEntity
        List<StockSymbolEntity> stockSymbols = objectMapper.readValue(inputStream, new TypeReference<List<StockSymbolEntity>>() {});

        // Check if each symbol exists before saving
        for (StockSymbolEntity stockSymbol : stockSymbols) {
            Optional<StockSymbolEntity> existingSymbol = stockSymbolRepository.findBySymbol(stockSymbol.getSymbol());
            if (existingSymbol.isEmpty()) {
                stockSymbolRepository.save(stockSymbol);
                System.out.println("Saved stock symbol: " + stockSymbol.getSymbol());
            } else {
                System.out.println("Skipped existing stock symbol: " + stockSymbol.getSymbol());
            }
        }

        System.out.println("Stock symbols processed successfully!");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
