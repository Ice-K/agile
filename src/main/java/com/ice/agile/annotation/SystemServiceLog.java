package com.ice.agile.annotation;

import java.lang.annotation.*;

/**
 * Cteated by wangpeng
 * 2018/3/2 11:35
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemServiceLog {
    int type(); // 操作类型
    String name() default ""; // eg：value = "添加用户" 用来说明实际操作
}
