package com.tyrone.blog.domain.response;

import lombok.Data;

import java.util.List;

/**
 * @author yingxiu.zty
 * @createTime on 2024/10/16
 * @description 分页响应类
 */
@Data
public class Pagination<T> {
    private List<T> records;
    private long current;
    private long size;
    private long total;
}
