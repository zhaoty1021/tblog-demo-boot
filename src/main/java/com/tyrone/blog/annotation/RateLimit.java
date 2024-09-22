package com.tyrone.blog.annotation;

import com.tyrone.blog.enums.RateLimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author yingxiu.zty
 * @createTime on 2024/9/23
 * @description 限流注解
 */
@Target({ElementType.METHOD}) // 指定该注解可以用在方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时保持
public @interface RateLimit {
    double LimitNum() default  10;      //默认每秒产生10个令牌
    RateLimitType type();
}

