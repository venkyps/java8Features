package com.learning.defaultandstatic;

import java.time.ZoneId;

public interface Interface2 {

    static void getCurrentTimeZone(){
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println("Current zone "+zoneId);
    }
}
