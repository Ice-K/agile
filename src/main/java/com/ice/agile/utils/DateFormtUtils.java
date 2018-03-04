package com.ice.agile.utils;

import java.util.Date;

/**
 * 时间处理工具类
 *
 * Cteated by wangpeng
 * 2018/2/26 16:06
 */
public class DateFormtUtils {

    /**
     * 获取精确到秒的时间戳
     * @return
     */
    public static int getSecondTimestamp() {
        Date now = new Date();
        return Integer.valueOf(String.valueOf(now.getTime()/1000));
    }


    /**
     * 获取精确到毫秒的时间戳
     * @return
     */
    public static long getTimestamp() {
        Date now = new Date();
        return Long.valueOf(now.getTime());
    }

}
