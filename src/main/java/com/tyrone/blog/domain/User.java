package com.tyrone.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yingxiu.zty
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -8239370740328279056L;
    private Integer id;

    private String username;

    private String nickname;

    private String password;

    private String email;

    private String registerIp;

    private String registerAddress;

    private Date activateTime;

    private Integer isDeleted;

    private Date createTime;

    private Date updateTime;
}