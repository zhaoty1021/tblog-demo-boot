package com.tyrone.blog.domain.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yingxiu.zty
 * @createTime on 2024/10/11
 * @description
 */
@Data
public class RoleVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5522909219413024098L;

    private String roleCode;
    private String roleName;
    private String description;

}
