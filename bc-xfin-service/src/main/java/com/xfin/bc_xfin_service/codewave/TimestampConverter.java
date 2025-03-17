package com.xfin.bc_xfin_service.codewave;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampConverter {
    public static String convertTimestamp(Long time, Timezone timezone) {
        Instant instant = Instant.ofEpochSecond(time);
        ZonedDateTime utcDateTime = instant.atZone(ZoneId.of(timezone.value));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = utcDateTime.format(formatter);

        return formattedTime;
    }
}
