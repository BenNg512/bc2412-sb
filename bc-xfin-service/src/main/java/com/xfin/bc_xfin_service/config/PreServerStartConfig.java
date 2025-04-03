package com.xfin.bc_xfin_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.xfin.bc_xfin_service.service.EntityService;

@Component
public class PreServerStartConfig implements CommandLineRunner {
  @Autowired
  private EntityService entityService;
  @Override
  public void run(String... args) throws Exception {
    Long currentTime = System.currentTimeMillis() / 1000;
    Long oneYearBefore = currentTime - 31536000;
    
    try{
        //this.entityService.saveStockSymbolsFromJson();
        //this.entityService.saveAllStockPriceFromApi();
        // fetch year to date data
        this.entityService.saveAllHistoricalData(oneYearBefore.toString(), currentTime.toString());
        this.entityService.redisSaveHistoryData();
        this.entityService.redisSaveLatestTransactionDayData();
    }catch(Exception e){ 
        throw e;
    }
    
  }
}
