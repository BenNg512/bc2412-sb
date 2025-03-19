package com.xfin.bc_xfin_service.config;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.xfin.bc_xfin_service.service.EntityService;

@Component
public class ScheduleConfig {

  @Autowired
  private EntityService entityService;

  
  @Scheduled(cron = "0 */5 9-17 * * MON-FRI", zone = "Asia/Hong_Kong")
  public void saveStockPrices() {
      this.entityService.saveAllStockPriceFromApi();
      System.out.println("5-mins data fetched");
      ZonedDateTime apiFetchTime = ZonedDateTime.now();
      String formattedFetchTime = apiFetchTime.withZoneSameInstant(ZoneId.of("Asia/Hong_Kong"))
                                  .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'HKT'"));
      System.out.println(formattedFetchTime);
  }

  @Scheduled(cron = "0 */5 * * * *", zone = "Asia/Hong_Kong")
  public void saveStockPricesBITC() {
    this.entityService.saveStockPriceFromApi("BITC");
    System.out.println("Bitcoin data fetched");
    ZonedDateTime apiFetchTime = ZonedDateTime.now();
    String formattedFetchTime = apiFetchTime.withZoneSameInstant(ZoneId.of("Asia/Hong_Kong"))
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'HKT'"));
    System.out.println(formattedFetchTime);
}

// if market is open at 9:30, clear previous day data
// 9-12, 12-16:30

//   @Scheduled(cron = "30 */1 9-17 * * MON-FRI", zone = "Asia/Hong_Kong")
//   public void clearOneDayData() {
//       try {
//           this.entityService.clearOneDayData();
//           System.out.println("One day data cleared");
//       } catch (Exception e) {
//           System.err.println("Error clearing one day data");
//           e.printStackTrace();
//       }
//   }

}
