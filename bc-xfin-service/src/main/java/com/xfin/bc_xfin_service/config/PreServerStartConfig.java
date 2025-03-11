package com.xfin.bc_xfin_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.xfin.bc_xfin_service.service.impl.EntityServiceImpl;

@Component
public class PreServerStartConfig implements CommandLineRunner {
  @Autowired
  private EntityServiceImpl entityService;

  @Override
  public void run(String... args) throws Exception {
    try{
        this.entityService.saveStockSymbolsFromJson();
        this.entityService.redisSaveStockSymbols();
    }catch(Exception e){
        throw e;
    }
    
  }
}
