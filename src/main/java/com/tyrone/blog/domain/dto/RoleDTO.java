package com.tyrone.blog.domain.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yingxiu.zty
 * @createTime on 2024/10/10
 * @description
 */
@Data
public class RoleDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -931828959714831334L;

    private String roleName;
    private String description;

}
