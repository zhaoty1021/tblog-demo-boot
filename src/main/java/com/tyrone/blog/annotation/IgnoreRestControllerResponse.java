package com.tyrone.blog.annotation;
import java.lang.annotation.*;

/**
 * @author yingxiu.zty
 * @createTime on 2024/7/28
 * @description 忽略响应封装注解
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreRestControllerResponse {
}

