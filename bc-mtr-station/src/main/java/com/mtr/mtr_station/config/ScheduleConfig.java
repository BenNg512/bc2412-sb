package com.mtr.mtr_station.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConfig {
  //@Scheduled(fixedDelay = 500)
  // @Scheduled(cron = "0 40 17 * * MON")
  public void sayHello(){
    System.out.println("Hello");
  }
  
  
}
