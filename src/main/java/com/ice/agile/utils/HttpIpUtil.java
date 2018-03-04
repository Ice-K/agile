package com.ice.agile.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 根据请求获取ip地址
 * Cteated by wangpeng
 * 2018/3/4 23:13
 */
public class HttpIpUtil {

    public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (checkIP(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (checkIP(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (checkIP(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (checkIP(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (checkIP(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip.equals("0:0:0:0:0:0:0:1")?"172.0.0.1":ip;

    }

    private static boolean checkIP(String ip) {
        return (ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip);
    }
}
