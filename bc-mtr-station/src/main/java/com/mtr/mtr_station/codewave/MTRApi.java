package com.mtr.mtr_station.codewave;

public class MTRApi {
  public static String getUrl(String line, String station) {
    String url = "https://rt.data.gov.hk/v1/transport/mtr/getSchedule.php" +
    "?line=" + line +
    "&sta=" + station;

    return url;
  }
  
}
