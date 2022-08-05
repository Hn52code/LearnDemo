package com.zhn.demo.webexample.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String nowTime(){
        return LocalDateTime.now().format(dateTimeFormatter);
    }

}
