package com.tyrone.blog.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
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

    @Serial
    private static final long serialVersionUID = -8239370740328279056L;
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String nickname;

    private String password;

    private int sex;

    private String email;

    private String phone;

    private String avatar;

    private String registerIp;

    private String registerAddress;

    private LocalDateTime activateTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}