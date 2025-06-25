package com.tyrone.blog.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author yingxiu.zty
 * @createDate on 2025/6/24
 * @createTime on 15:30
 * 用于创建/更新文章
 */
@Data
public class ArticleDTO {
    private Long id;

    @NotBlank(message = "文章标题不能为空")
    private String articleTitle;

    @NotBlank(message = "文章内容不能为空")
    private String articleContent;

    private String articleCover;
    private String articleDesc;

    @NotNull(message = "文章类型不能为空")
    private Integer articleType;

    @NotNull(message = "是否置顶不能为空")
    private Integer isTop;

    @NotNull(message = "文章状态不能为空")
    private Integer status;

    private Long categoryId;
    private String tags;
}
