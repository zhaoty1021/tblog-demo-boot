package com.tyrone.blog.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author yingxiu.zty
 * @createDate on 2025/6/24
 * @createTime on 15:32
 */
@Data
public class ArticleListVO {
    private Long id;
    private String articleTitle;
    private String articleCover;
    private String articleDesc;
    private Integer articleType;
    private Integer isTop;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private String categoryName;
    private List<String> tagNames;
}
