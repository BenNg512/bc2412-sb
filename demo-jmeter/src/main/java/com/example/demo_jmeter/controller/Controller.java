package com.example.demo_jmeter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class Controller {
  public static int availableStock = 0;
  public static int orderCount = 0;

  @GetMapping(value = "/info")
    public String getInfo(){
      return "availableStock: " + availableStock + ", orderCount: " + orderCount;
    }
    

  @GetMapping(value = "/buy")
    public boolean buy(){
      if (payment()){
        availableStock--;
        orderCount++;
        return true;
      }
      return false;
    }
  
    private boolean payment(){
      try{
        Thread.sleep(20);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return true;
    }
  
  
}
