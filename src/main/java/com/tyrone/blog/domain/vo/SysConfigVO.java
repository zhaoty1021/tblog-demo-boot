package com.tyrone.blog.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yingxiu.zty
 * @createDate on 2025/6/8
 * @createTime on 01:45
 */
@Data
public class SysConfigVO implements Serializable {

    private static final long serialVersionUID = -4779949087770277629L;
    /**
     * 配置ID
     */
    private Long id;

    /**
     * 配置键
     */
    private String configKey;

    /**
     * 配置值
     */
    private String configValue;

    /**
     * 配置类型(TEXT/NUMBER/BOOLEAN/JSON)
     */
    private String configType;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否系统配置(0:否,1:是)
     */
    private Integer isSystem;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
