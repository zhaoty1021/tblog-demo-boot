package com.tyrone.blog.aspect;
import com.alibaba.fastjson.JSON;
import com.tyrone.blog.annotation.SysLog;
import com.tyrone.blog.domain.Log;
import com.tyrone.blog.service.LogService;
import com.tyrone.blog.utils.DateFormatUtil;
import com.tyrone.blog.utils.IpUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Arrays;

/**
 * @author yingxiu.zty
 * @createTime on 2024/7/27
 * 操作日志aop
 */
@Component
@Slf4j
@Aspect
public class LogAspect {
    @Resource
    private LogService logService;
    //定义切点 切点表达式指向SysLog注解，我们再业务方法上可以加上SysLog注解，然后所标注
    //的方法都能进行日志记录
    @Pointcut("@annotation(com.tyrone.blog.annotation.SysLog)")
    public void logPointCut() {
    }
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }
    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) throws Throwable {

        // 执行方法
        Object result = joinPoint.proceed();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Log sysLog = new Log();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if(syslog != null){
            //注解上的描述
            sysLog.setOperation(syslog.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        sysLog.setReturnParams(result.getClass().getName());
        if (syslog != null) {
            sysLog.setOperation(syslog.value());
        }
        //请求的参数
        Object[] args = joinPoint.getArgs();
        try{
            String params = Arrays.toString(args);
            sysLog.setRequestParams(args.getClass().getName());
        }catch (Exception e){

        }


        //设置IP地址

        sysLog.setTime(time);
        //保存系统日志
        logService.save(sysLog);
    }

}
