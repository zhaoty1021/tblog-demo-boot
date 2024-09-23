package com.tyrone.blog.aspect;

import com.google.common.util.concurrent.RateLimiter;
import com.tyrone.blog.annotation.RateLimit;
import com.tyrone.blog.enums.CodeEnum;
import com.tyrone.blog.enums.RateLimitType;
import com.tyrone.blog.exceptions.BizException;
import com.tyrone.blog.utils.IpUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @author yingxiu.zty
 * @createTime on 2024/9/23
 * @description
 */
@Aspect
@Component
public class RateLimiterAspect {

    private final ConcurrentHashMap<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();

    private RateLimiter rateLimiter; // 默认的RateLimiter配置，用于方法中没有指定RateLimiter Bean的名称时

    private HttpServletRequest request;

    @Pointcut("@annotation(com.tyrone.blog.annotation.RateLimit)")
    public void rateLimit() {

    }

    @Around("rateLimit()")
    public Object pointcut(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RateLimit rateLimit = method.getAnnotation(RateLimit.class);
        if (rateLimit != null) {
            double value = rateLimit.LimitNum();
            RateLimitType type = rateLimit.type();

            // 根据注解的值获取对应的RateLimiter
            if(type.equals(RateLimitType.DEFAULT)){
                RateLimiter limiter = rateLimiterMap.computeIfAbsent(
                        signature.toLongString(),
                        k -> RateLimiter.create(value)
                );
                // 尝试获取令牌，无法获取则抛出异常
                if (!limiter.tryAcquire()) {
                    throw new BizException(CodeEnum.RATE_LIMIT_ERROR);
                }
            }else{
                if(type.equals(RateLimitType.IP)){
                    String ip = IpUtil.getIpAddr(request);
                    String key = ip + ":" + signature.getMethod().getName();
                    RateLimiter limiter = rateLimiterMap.computeIfAbsent(
                            key,
                            k -> RateLimiter.create(value)
                    );
                    if (!limiter.tryAcquire()) {
                        throw new BizException(CodeEnum.RATE_LIMIT_ERROR);
                    }
                }
            }




        }

        return joinPoint.proceed(); // 执行目标方法
    }
}
