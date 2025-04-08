package com.tyrone.blog.domain.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yingxiu.zty
 * @createDate on 2025/4/9
 * @createTime on 00:19
 * 评论类
 */
@Document(collection = "comments")
@Data
public class Comment implements Serializable {
    @Serial
    private static final long serialVersionUID = -6888666347902492619L;
    /*
    * 主键id
    */
    @Id
    private String id;
    private String postId;
    private String content;
    private Long userId;  // 仅存储MySQL用户ID
    private List<Comment> replies;
    private LocalDateTime createTime;

    // 不嵌入用户信息
}