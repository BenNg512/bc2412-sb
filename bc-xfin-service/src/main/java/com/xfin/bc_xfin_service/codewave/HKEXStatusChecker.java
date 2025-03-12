package com.xfin.bc_xfin_service.codewave;

import java.time.*;

public class HKEXStatusChecker {
    public static boolean isHKEXOpen() {
        // Set Hong Kong timezone
        ZoneId hkZone = ZoneId.of("Asia/Hong_Kong");
        LocalDateTime now = LocalDateTime.now(hkZone);
        
        // Extract time and weekday
        LocalTime time = now.toLocalTime();
        int weekday = now.getDayOfWeek().getValue(); // 1 = Monday, 7 = Sunday
        
        // Define trading hours
        LocalTime morningOpen = LocalTime.parse("09:30");
        LocalTime lunchClose = LocalTime.parse("12:00");
        LocalTime afternoonOpen = LocalTime.parse("13:00");
        LocalTime marketClose = LocalTime.parse("16:00");
        
        // Check if it's a weekday (Monday = 1 to Friday = 5)
        if (weekday <= 5) {
            // Check if time is within morning or afternoon session
            if ((time.compareTo(morningOpen) >= 0 && time.compareTo(lunchClose) < 0) ||
                (time.compareTo(afternoonOpen) >= 0 && time.compareTo(marketClose) < 0)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        boolean isOpen = isHKEXOpen();
        System.out.println("HKEX open now? " + isOpen);
    }
}
