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
    ERROR("500","系统异常，请稍后重试"),
    /*业务错误码**/
    MISSING_PARAMETER("100001","缺少参数"),
    LOGIN_ERROR("100002","用户名或密码错误"),
    OUTSIDE_API_ERROR("100003","外部API调用失败"),
    DATA_NOT_EXIST("100004","数据不存在"),
    LOGIN_TOKEN_ERROR("100005","登录令牌错误"),
    USERNAME_EXIST("100006","用户名已存在"),

    /* 限流 **/
    RATE_LIMIT_ERROR("200001","操作过于频繁，请稍后重试"),

    /* minio相关 */
    BUCKET_NOT_EXIST("300001", "minio的bucket不存在"),
    UPLOAD_FILE_ERROR("300002", "上传文件失败"),
    DOWNLOAD_FILE_ERROR("300003", "下载文件失败"),
    GET_FILE_URL_ERROR("300004", "获取文件url失败"),

    /** redis 错误码 **/
    REDIS_UNKNOWN_ERROR("400001", "Redis未知错误"),
    REDIS_CONNECTION_ERROR("400002", "Redis连接错误"),
    REDIS_OPERATION_ERROR("400003", "Redis操作错误"),
    REDIS_KEY_NOT_FOUND("400004", "Redis键不存在"),
    REDIS_DATA_TYPE_ERROR("400005", "Redis数据类型错误"),
    REDIS_SERIALIZATION_ERROR("400006", "Redis序列化/反序列化错误");






    /**
     * code
     */
    private final String code;

    /**
     * Msg
     */
    private final String message;
}
