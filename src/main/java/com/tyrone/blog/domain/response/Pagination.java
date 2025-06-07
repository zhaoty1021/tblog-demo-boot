package com.tyrone.blog.domain.response;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yingxiu.zty
 * @createTime on 2024/10/16
 * @updateTime  on 2025/04/09
 * @description 分页响应类
 */
@Data
@NoArgsConstructor
public class Pagination<T> {
    private long current;    // 当前页
    private long size;       // 每页数量
    private long total;      // 总数
    private long pages;      // 总页数
    private List<T> records; // 数据列表
    // 从MyBatis-Plus的Page转换
    public Pagination(Page<T> page) {
        this.current = page.getCurrent();
        this.size = page.getSize();
        this.total = page.getTotal();
        this.pages = page.getPages();
        this.records = page.getRecords();
    }

    // 从Spring Data的Page转换
    public Pagination(org.springframework.data.domain.Page<T> page) {
        this.current = page.getNumber() + 1; // Spring Data页码从0开始
        this.size = page.getSize();
        this.total = page.getTotalElements();
        this.pages = page.getTotalPages();
        this.records = page.getContent();
    }
}
