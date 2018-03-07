package com.ice.agile.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间处理工具类
 *
 * Cteated by wangpeng
 * 2018/2/26 16:06
 */
public class DateFormtUtil {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 获取当前时间戳
     * @return 当前时间戳
     */
    public static Timestamp getTimeStamp() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * 获取当前日期的字符串，格式为"yyyy-MM-dd"
     * @return 当前时间的字符串
     */
    public static String getDateStr() {
        return dateFormat.format(new Date());
    }

    /**
     * 获取指定日期的字符串，格式为"yyyy-MM-dd"
     * @param date 指定的日期
     * @return 指定日期的字符串
     */
    public static String getDateStr(Date date) {
        return dateFormat.format(date);
    }

    /**
     * 获取当前时间的字符串 格式为"yyyy-MM-dd HH:mm:ss"
     * @return 当前时间的字符串
     */
    public static String getDateTimeStr() {
        return dateTimeFormat.format(new Date());
    }

    /**
     * 获取指定时间的字符串 格式为"yyyy-MM-dd HH:mm:ss"
     * @return 当前时间的字符串
     */
    public static String getDateTimeStr(Date date) {
        return dateTimeFormat.format(date);
    }


    /**
     * 将指定的字符串转换为日期
     * @param dateStr 日期字符串格式为 "yyyy-MM-dd"
     * @return 指定的日期
     */
    public static Date getDate(String dateStr) throws ParseException {
        return dateFormat.parse(dateStr);
    }

    /**
     * 将指定的字符串转换为日期
     * @param dateTimeStr 时间字符串格式为 "yyyy-MM-dd HH:mm:ss"
     * @return 指定的时间
     */
    public static Date getDateTime(String dateTimeStr) throws ParseException {
        return dateTimeFormat.parse(dateTimeStr);
    }
}
