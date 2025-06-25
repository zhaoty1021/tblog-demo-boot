package com.tyrone.blog.domain.vo;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * @author yingxiu.zty
 * @createDate on 2025/6/24
 * @createTime on 15:31
 */
@Data
public class ArticleVO {
    private Long id;
    private String articleTitle;
    private String articleContent;
    private String articleCover;
    private String articleDesc;
    private Integer articleType;
    private Integer isTop;
    private Integer status;
    private Long categoryId;
    private String categoryName;
    private List<String> tagNames;
    private Date createTime;
    private Date updateTime;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
}
