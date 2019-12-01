package com.learning.defaultandstatic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@FunctionalInterface
public interface Interface1 {
    void log();
    default void getLocalTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalTime localTime = localDateTime.toLocalTime();
        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println("LocalTime :: "+ localTime);
        System.out.println("LocalDate :: "+ localDate);
    }
}
