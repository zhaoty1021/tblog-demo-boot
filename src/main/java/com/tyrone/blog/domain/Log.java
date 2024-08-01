package com.tyrone.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName t_log
 */
@TableName(value ="t_log")
@Data
public class Log implements Serializable {
    private static final long serialVersionUID = -717599491247756647L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String username;

    private String operation;

    private String method;

    private String requestParams;

    private String returnParams;

    private long time;

    private String ip;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;


}