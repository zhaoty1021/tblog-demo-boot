package com.tyrone.blog.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tyrone.blog.annotation.IgnoreRestControllerResponse;
import com.tyrone.blog.domain.response.ResultResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author yingxiu.zty
 * @createTime on 2024/7/28
 * @description 全局响应拦截
 */
@RestControllerAdvice(basePackages = {"com.tyrone.blog.controller"})
public class GlobalResponseAspect implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 方法没有IgnoreRestControllerResponse注解，且response不是ResultResponse类型时启用beforeBodyWrite
        return !returnType.hasMethodAnnotation(IgnoreRestControllerResponse.class)
                && !returnType.getParameterType().isAssignableFrom(ResultResponse.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 如果返回值是void类型，直接返回200状态信息
        if (returnType.getParameterType().isAssignableFrom(void.class)) {
            return ResultResponse.success();
        }
        if (!(body instanceof ResultResponse)) {
            // 如果返回值不是ResultResponse类型，则包装成ResultResponse类型
            return ResultResponse.success(body);
        }
        return body;
    }
}
