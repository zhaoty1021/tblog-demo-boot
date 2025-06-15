package com.tyrone.blog.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yingxiu.zty
 * @createTime on 2024/9/1
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    // 注册 Sa-Token 拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor(handle -> {
                    // ✅ 获取当前请求对象
                    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

                    // ✅ 放行预检请求
                    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
                        return;
                    }

                    // 校验是否已登录
                    StpUtil.checkLogin();
                }))
                //所有接口都会检查是否登录了
                .addPathPatterns("/api/**")
                //以下接口不检查，直接放行
                .excludePathPatterns("/**/doc.*",
                        "/**/swagger-ui.*",
                        "/**/swagger-resources",
                        "/**/webjars/**",
                        "/**/v2/api-docs/**",
                        "/api/register",
                        "/api/login");


    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 或者更具体，如 /api/**
                .allowedOrigins("http://localhost:3006","http://124.70.85.121") // 前端地址
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
