package com.zhn.demo.netty.netty2.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author zhn
 */
public class TimeUtil {

    private static SimpleDateFormat format = null;

    private static String defaultFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * 说明：给定时间格式，返回时间的字符串
     */
    public static String getDateToString(Date date, String... dateFormat) {
        if (dateFormat.length > 0)
            defaultFormat = dateFormat[0];
        format = new SimpleDateFormat(defaultFormat);
        return format.format(date);
    }

    /**
     * 说明：将字符串转为日期格式
     */
    public static Date getDateFromString(String date) {
        format = new SimpleDateFormat(defaultFormat);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}