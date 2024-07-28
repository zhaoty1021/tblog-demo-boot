package com.tyrone.blog.annotation;

import java.lang.annotation.*;

/**
 * @author yingxiu.zty
 * @createTime on 2024/7/27
 * 系统日志注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String operation() default "";
    String value() default "";
}
