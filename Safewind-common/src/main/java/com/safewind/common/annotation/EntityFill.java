package com.safewind.common.annotation;

import java.lang.annotation.*;

/**
 * @Author: Darven
 * @CreateTime: 2025-06-26  20:55
 * @Description: 填充基础类反射
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface EntityFill {
}
