package com.tyrone.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yingxiu.zty
 * @createTime on 2024/7/28
 * 错误码枚举类
 */
@Getter
@AllArgsConstructor
public enum CodeEnum {
    /**
     * 请求成功
     */
    SUCCESS("200", "请求成功"),
    /**
     * 请求失败
     */
    FAILURE("201", "请求失败"),
    /**系统相关错误码**/
    ERROR("500","系统异常，请稍后重试");

    /**
     * code
     */
    private final String code;

    /**
     * Msg
     */
    private final String message;
}
