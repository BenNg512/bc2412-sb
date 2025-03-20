package com.example.demo_jmeter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  public static int availableStock = 1000;
  //public static AtomicInteger availableStock = new AtomicInteger(1000);
  public static int orderCount = 0;

  @GetMapping(value = "/info")
    public String getInfo(){
      return "availableStock: " + availableStock + ", orderCount: " + orderCount;
    }
  
  @GetMapping("/syncBuy")
  public String syncBuy(){
    availableStock--;
    if (availableStock >= 0 && payment()){
      orderCount++;
      return "TRUE " + getInfo();
    }else{
      availableStock++;
      return "FALSE " + getInfo();
    }
    
  }

  @GetMapping(value = "/buy")
    public String buy(){
      if (availableStock >= 1 && payment()){
        availableStock--;
        orderCount++;
        return "TRUE " + getInfo();
      }
      return "FALSE " + getInfo();
    }
  
    private boolean payment(){
      try{
        Thread.sleep(20);
      } catch (Exception e) {
        return false;
      }
      return true;
    }
  
  
}
