package com.safewind.common.annotation;

import java.lang.annotation.*;
/**
 * 匿名访问注解
 * 添加该注释不需要登录就可以访问
 * 用于网站预览资源
 * */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Anonymous {
}
