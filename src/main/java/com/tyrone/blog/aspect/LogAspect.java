package com.tyrone.blog.aspect;
import com.tyrone.blog.exceptions.BizException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yingxiu.zty
 * @createTime on 2024/7/27
 * 操作日志aop
 */
@Component
@Slf4j
@Aspect
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    //定义切点 切点表达式指向SysLog注解，我们再业务方法上可以加上SysLog注解，然后所标注的方法都能进行日志记录
    @Pointcut("@annotation(com.tyrone.blog.annotation.SysLog)")
    public void logPointCut() {
    }

    // 方法执行前记录日志
    @Before("logPointCut()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();  // 获取方法参数
        // 对参数进行脱敏处理
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String && args[i].toString().toLowerCase().contains("password")) {
                args[i] = "******";  // 将密码等敏感信息脱敏
            }
        }
        logger.info("Executing method: {} with arguments: {}",
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    // 方法执行后记录日志
    @AfterReturning(pointcut = "logPointCut()", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        Object[] args = joinPoint.getArgs();  // 获取方法参数
        // 对参数进行脱敏处理
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String && args[i].toString().toLowerCase().contains("password")) {
                args[i] = "******";  // 将密码等敏感信息脱敏
            }
        }
        logger.info("Method executed: {} with return value: {}",
                joinPoint.getSignature().toShortString(),
                result);
    }

    // 捕捉异常时记录日志
    @AfterThrowing(pointcut = "logPointCut()", throwing = "exception")
    public void logAfterException(JoinPoint joinPoint, BizException exception) {
        // 获取堆栈信息
        StackTraceElement[] stackTrace = exception.getStackTrace();
        // 限制堆栈深度
        if (stackTrace.length > 5) {
            StackTraceElement[] limitedStackTrace = new StackTraceElement[5];
            System.arraycopy(stackTrace, 0, limitedStackTrace, 0, 5);
            exception.setStackTrace(limitedStackTrace);
        }
        logger.error("Method: {} threw exception: {}",
                joinPoint.getSignature().toShortString(),
                exception.getMessage(),exception);
    }

}
