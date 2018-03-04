package com.ice.agile.annotation;

import java.lang.annotation.*;

/**
 * Cteated by wangpeng
 * 2018/3/2 11:35
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutomaticLog {
    String name() default ""; // eg：value = "用户管理" 用来说明实际操作的模块
}
