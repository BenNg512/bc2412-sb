package com.xfin.bc_xfin_service.codewave;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampConverter {
    public static String convertTimestampToUTC(Long time) {

        Instant instant = Instant.ofEpochSecond(time);
        ZonedDateTime utcDateTime = instant.atZone(ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = utcDateTime.format(formatter);

        return String.valueOf(formattedTime);
    }
    public static String convertTimestampToHKT(Long time) {

        Instant instant = Instant.ofEpochSecond(time);
        ZonedDateTime utcDateTime = instant.atZone(ZoneId.of("Asia/Hong_Kong"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = utcDateTime.format(formatter);

        return String.valueOf(formattedTime);
    }
}
