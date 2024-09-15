package com.tyrone.blog.exceptions;

import com.tyrone.blog.enums.CodeEnum;
import lombok.Data;

/**
 * @author yingxiu.zty
 * @createTime on 2024/7/28
 */
@Data
public class BizException extends RuntimeException{
    /**
     * 错误编码
     */
    private String code;

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(CodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
    }
    public BizException(CodeEnum codeEnum, String message) {
        super(codeEnum.getMessage() + message);
        this.code = codeEnum.getCode();
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(String message, Throwable cause,
                             boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String toString() {
        return this.code + ":" + this.getMessage();
    }

}
