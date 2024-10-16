package com.tyrone.blog.domain.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yingxiu.zty
 * @createTime on 2024/9/22
 */
@Data
public class UserVO implements Serializable{
    @Serial
    private static final long serialVersionUID = 2354394099803612660L;
    private String username;

    private String nickname;

    private String email;

    private int sex;

    private String phone;

    private String avatar;

    private String registerIp;

    private String registerAddress;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
