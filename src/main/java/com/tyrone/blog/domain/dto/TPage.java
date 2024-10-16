package com.tyrone.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yingxiu.zty
 * @createTime on 2024/10/16
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TPage implements Serializable {
    @Serial
    private static final long serialVersionUID = -8132242281761915487L;
    private int currentPage = 1;
    private int pageSize = 10;
}
