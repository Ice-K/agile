package com.ice.agile.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Druid的DataResource配置类
 *
 * 凡是被Spring管理的类，实现接口 EnvironmentAware
 * 重写 setEnvironment 可以在工程启动时，获取到系统
 * 环境变量和applicaiton配置文件中的变量。还有一种方
 * 式是采用注解的方式获取 @Value("${变量的key值}")
 *
 * 获取application配置文件中的变量。这里采用第一种要方便些
 * Cteated by wangpeng
 * 2018/2/26 14:05
 */
//@Configuration
//@EnableTransactionManagement
//@Slf4j
public class DruidDataSourceConfig implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
    }

    /**
     * 配置数据源
     * @return
     */
    //@Bean
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(propertyResolver.getProperty("url"));//url
        datasource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));//驱动名称
        datasource.setUsername(propertyResolver.getProperty("username"));//数据库用户名
        datasource.setPassword(propertyResolver.getProperty("password"));//数据库密码
        datasource.setInitialSize(Integer.valueOf(propertyResolver.getProperty("initialSize")));//初始化链接数
        datasource.setMinIdle(Integer.valueOf(propertyResolver.getProperty("minIdle")));//最小连接数
        datasource.setMaxActive(Integer.valueOf(propertyResolver.getProperty("maxActive")));//最大连接数
        datasource.setMaxWait(Long.valueOf(propertyResolver.getProperty("maxWait")));//配置获取链接等待超时的时间 单位毫秒
        datasource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver.getProperty("minEvictableIdleTimeMillis")));//配置一个连接在池中最小生存的时间，单位是毫秒
        datasource.setTimeBetweenEvictionRunsMillis(Long.valueOf(propertyResolver.getProperty("timeBetweenEvictionRunsMillis")));//配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        String cp = propertyResolver.getProperty("connectionProperties");//通过connectProperties属性来打开mergeSql功能；慢SQL记录
        String[] cps = cp.split("=|;");
        Properties connectionProperties = new Properties();
        for (int i = 0;i < cps.length; i+=2) {
            connectionProperties.setProperty(cps[i],cps[i+1]);
        }
        datasource.setConnectProperties(connectionProperties);
        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //log.info("===数据源初始化完成===");
        return datasource;
    }

    /**
     * 配置监控器登录
     * @return
     */
    //@Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername","druid");    //用户名
        initParameters.put("loginPassword","druid");    //密码
        initParameters.put("resetEnable","false");      //禁用HTML页面上的“reset All”功能
        initParameters.put("allow","127.0.0.1");        //IP白名单（没有配置或者为空，则允许所有访问）
        initParameters.put("deny","192.168.20.38");     //IP黑名单（allow 有限于 deny）
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    /**
     * 配置监控器
     * @return
     */
    //@Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    // 按照BeanId来拦截配置，用来bean的监控
    //@Bean(value = "druid-stat-interceptor")
    public DruidStatInterceptor druidStatInterceptor() {
        DruidStatInterceptor druidStatInterceptor = new DruidStatInterceptor();
        return druidStatInterceptor;
    }

    //配置spring监控
    //@Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        beanNameAutoProxyCreator.setProxyTargetClass(true);
        //设置要监控的bean的id
        //beanNameAutoProxyCreator.setBeanNames("");
        beanNameAutoProxyCreator.setInterceptorNames("druid-stat-interceptor");
        return beanNameAutoProxyCreator;
    }
}
