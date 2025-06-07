package com.tyrone.blog.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author yingxiu.zty
 * @createDate on 2025/6/8
 * @createTime on 01:42
 */
@Data
public class SysConfigDTO {

    /**
     * 配置键
     */
    @NotBlank(message = "配置键不能为空")
    private String configKey;

    /**
     * 配置值
     */
    @NotBlank(message = "配置值不能为空")
    private String configValue;

    /**
     * 配置类型(TEXT/NUMBER/BOOLEAN/JSON)
     */
    @NotBlank(message = "配置类型不能为空")
    private String configType;

    /**
     * 分组名称
     */
    @NotBlank(message = "分组名称不能为空")
    private String groupName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否系统配置(0:否,1:是)
     */
    @NotNull(message = "是否系统配置不能为空")
    private Integer isSystem;
}
