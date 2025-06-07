package com.tyrone.blog.domain.dto;

import lombok.Data;

/**
 * @author yingxiu.zty
 * @createDate on 2025/6/8
 * @createTime on 01:39
 */
@Data
public class SysConfigQueryDTO {

    /**
     * 配置键
     */
    private String configKey;

    /**
     * 配置类型
     */
    private String configType;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 是否系统配置(0:否,1:是)
     */
    private Integer isSystem;
}
