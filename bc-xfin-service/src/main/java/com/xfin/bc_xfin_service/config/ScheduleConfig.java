package com.xfin.bc_xfin_service.config;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.xfin.bc_xfin_service.service.EntityService;

@Component
public class ScheduleConfig {

  @Autowired
  private EntityService entityService;
  Long currentTime = System.currentTimeMillis() / 1000;
  Long oneYearBefore = currentTime - 31536000;

  // every 5 minutes update 5-mins data
  @Scheduled(cron = "0 */1 9-17 * * MON-FRI", zone = "Asia/Hong_Kong")
  public void saveStockPrices() throws JsonProcessingException {
      this.entityService.saveAllStockPriceFromApi();
      System.out.println("5-mins data fetched");

      ZonedDateTime apiFetchTime = ZonedDateTime.now();
      String formattedFetchTime = apiFetchTime.withZoneSameInstant(ZoneId.of("Asia/Hong_Kong"))
                                  .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'HKT'"));
      System.out.println(formattedFetchTime);
  }

  // every minute update bitcoin data
  @Scheduled(cron = "0 * * * * *", zone = "Asia/Hong_Kong")
  public void saveStockPricesBITC() {
    this.entityService.saveStockPriceFromApi("BTC-USD");
    System.out.println("Bitcoin data fetched");
    ZonedDateTime apiFetchTime = ZonedDateTime.now();
    String formattedFetchTime = apiFetchTime.withZoneSameInstant(ZoneId.of("Asia/Hong_Kong"))
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'HKT'"));
    System.out.println(formattedFetchTime);
  }

// every hour update history data
  @Scheduled(cron = "0 0 * * * *", zone = "Asia/Hong_Kong")
  public void redisUpdateHistoryData() throws JsonProcessingException {
    this.entityService.redisSaveHistoryData();
  }

  @Scheduled(cron = "0 0 12 1 * *", zone = "Asia/Hong_Kong")
  public void getUpdateHistoryData() throws JsonProcessingException {
    this.entityService.saveAllHistoricalData(oneYearBefore.toString(), currentTime.toString());
  }

  @Scheduled(cron = "0 */5 * * * *", zone = "Asia/Hong_Kong")
  public void redisUpdateLatestTransactionDayData() throws JsonProcessingException {
  this.entityService.redisSaveLatestTransactionDayData();
  System.out.println("Redis 5-mins data fetched");
  }

}
