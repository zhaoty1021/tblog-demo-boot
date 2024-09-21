package com.tyrone.blog.domain.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yingxiu.zty
 * @createTime on 2024/9/21
 */
@Data
public class LoginDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 895764338545590045L;
    private String username;

    private String password;

    private String nickname;

    private String email;

    private String registerIp;

    private String registerAddress;
}
