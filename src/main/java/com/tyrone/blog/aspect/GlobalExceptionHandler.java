package com.tyrone.blog.aspect;

import com.tyrone.blog.domain.response.ResultResponse;
import com.tyrone.blog.exceptions.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author yingxiu.zty
 * @createTime on 2024/9/16
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BizException.class)
    public ResultResponse<String> handleIllegalArgumentException(BizException e) {
        // 记录异常日志
        logger.error("BizException: {}", e.getMessage(), e);
        return ResultResponse.error(e);
    }
}
