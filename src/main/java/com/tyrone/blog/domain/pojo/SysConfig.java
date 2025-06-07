package com.tyrone.blog.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统配置表
 * @TableName t_sys_config
 */
@TableName(value ="t_sys_config")
@Data
public class SysConfig implements Serializable {


    /**
     * 
     */
    @TableId(type = IdType.AUTO)
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
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    private static final long serialVersionUID = -6920922002074336093L;
}