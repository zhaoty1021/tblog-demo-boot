package com.tyrone.blog.enums;

/**
 * @author yingxiu.zty
 * @createTime on 2024/9/23
 * @description 限流类型枚举类
 */
public enum RateLimitType {
    IP,
    USER_ID,
    HEADER,
    CUSTOM // 可以根据需要扩展更多类型
}
