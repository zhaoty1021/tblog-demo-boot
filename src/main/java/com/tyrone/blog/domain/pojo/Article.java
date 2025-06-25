package com.tyrone.blog.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 文章表
 * @TableName t_article
 */
@TableName(value ="t_article")
@Data
public class Article implements Serializable {
    @Serial
    private static final long serialVersionUID = 872283139735939159L;
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 缩略图
     */
    private String articleCover;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章摘要
     */
    private String articleDesc;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 类型 (1原创 2转载 3翻译)
     */
    private Integer articleType;

    /**
     * 是否置顶 (0否 1是）
     */
    private Integer isTop;

    /**
     * 状态 (1公开 2私密 3评论可见)
     */
    private Integer status;

    /**
     * 最终修改人
     */
    private String updateBy;

    /**
     * 是否被删除
     */
    private Integer isDeleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}