package com.ice.agile.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Description: 读取properties文件工具
 * Cteated by wangpeng
 * 2018/3/4 9:29
 */
@Slf4j
public class PropertiesUtil {

    private static Properties properties = new Properties();

    static {
        log.info("System ===> 配置文件开始加载...");
        try {
            properties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties"), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("System ===> 配置文件加载失败！msg："+e.getMessage());
        }
        log.info("System ===> 配置文件加载成功！");
    }

    /**
     * 根据key获取value
     * @param key 传入的key
     * @return    key对应的value
     */
    public static String getProperty(String key) {

        return properties.getProperty(key);

    }
}
