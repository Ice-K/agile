package com.ice.agile.configuration;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 *
 *
 * Cteated by wangpeng
 * 2018/2/28 13:14
 */
@Configuration
public class MybatisConfig {

    /**
     * 注册分页插件
     */
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        //添加配置 也可以指定文件路径
        Properties p = new Properties();
        p.setProperty("helperDialect","mysql");//方言
        p.setProperty("supportMethodsArguments","true");
        p.setProperty("params","count=countSql");
        p.setProperty("reasonable","true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
