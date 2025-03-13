package com.xfin.bc_xfin_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.xfin.bc_xfin_service.codewave.RedisManager;
import com.xfin.bc_xfin_service.service.EntityService;

@Component
public class PreServerStartConfig implements CommandLineRunner {
  @Autowired
  private EntityService entityService;
  @Autowired
  private RedisManager redisManager;

  @Override
  public void run(String... args) throws Exception {
    try{
        //this.entityService.saveStockSymbolsFromJson();
        this.redisManager.clearAllData();
        this.entityService.redisSaveStockSymbols();
        //this.entityService.savePHEntity();
        this.entityService.saveAllStockPriceFromApi();
    }catch(Exception e){
        throw e;
    }
    
  }
}
