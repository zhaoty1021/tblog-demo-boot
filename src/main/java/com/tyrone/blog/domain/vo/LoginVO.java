package com.tyrone.blog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yingxiu.zty
 * @createTime on 2024/9/21
 */
@AllArgsConstructor
@Data
public class LoginVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4931265560898826335L;

    private String username;

    private String nickname;

    private String email;

    private String registerIp;

    private String registerAddress;
}
