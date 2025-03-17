package com.xfin.bc_xfin_service.codewave;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampConverter {

    // custom pattern
    public static String convertTimestamp(Long time, Timezone timezone, String pattern) {
        Instant instant = Instant.ofEpochSecond(time);
        ZonedDateTime utcDateTime = instant.atZone(ZoneId.of(timezone.value));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formattedTime = utcDateTime.format(formatter);

        return formattedTime;
    }
}
