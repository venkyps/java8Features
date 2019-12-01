package com.learning.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeExample {


    public static void main(String[] args) {
        //Current Date
        LocalDate todayDate = LocalDate.now();
        System.out.println("Current Date="+todayDate);

        //Creating LocalDate by providing input arguments
        LocalDate firstDay_2014 = LocalDate.of(2014, Month.JANUARY, 1);
        System.out.println("Specific Date="+firstDay_2014);

        //Current date in "Asia/Kolkata", you can get it from ZoneId javadoc
        LocalDateTime todayKolkata = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("Current Date in IST="+todayKolkata);

        //Getting date from the base date i.e 01/01/1970
        LocalDate dateFromBase = LocalDate.ofEpochDay(365);
        System.out.println("365th day from base date= "+dateFromBase);

        LocalDate hundredDay2014 = LocalDate.ofYearDay(2014, 100);
        System.out.println("100th day of 2014="+hundredDay2014);

        //unix date format
        Instant timestamp = Instant.now();
        System.out.println("Unix Current Timestamp = "+timestamp);

        LocalDate today = LocalDate.now();
        System.out.println("Year "+today.getYear()+" is Leap Year? "+today.isLeapYear());

        //Compare two LocalDate for before and after
        System.out.println("Today is before 01/01/2015? "+today.isBefore(LocalDate.of(2015,1,1)));
        System.out.println("10 days after today will be "+today.plusDays(10));
        System.out.println("7 days before today will be "+today.minusDays(7));

        LocalDate date = LocalDate.now();
        System.out.println("Default format of LocalDate="+date);
        //specific format
        System.out.println("specific format "+date.format(DateTimeFormatter.ofPattern("dd-MM-yy")));
        System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("timestamp in specific format "+dateTime.format(DateTimeFormatter.ofPattern("dd-MMM-uuuu HH::mm::ss")));

        LocalDateTime dt = LocalDateTime.parse("27::Apr::2014 21::39::48",
                DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss"));
        System.out.println("Default format after parsing = "+dt);
        System.out.println("specific format after parsing = "+dt.format(DateTimeFormatter.ofPattern("dd-MM-uu HH::mm::ss")));



    }
}
