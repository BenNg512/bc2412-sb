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
    
    try{
        //this.entityService.saveStockSymbolsFromJson();
        //this.entityService.savePHEntity();
        this.entityService.redisSaveStockSymbols();
        this.entityService.saveAllStockPriceFromApi();
    }catch(Exception e){ 
        throw e;
    }
    
  }
}
