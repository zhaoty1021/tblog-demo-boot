package com.tyrone.blog.mapper.mongo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tyrone.blog.domain.pojo.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author yingxiu.zty
 * @createDate on 2025/4/9
 * @createTime on 00:19
 */
public interface CommentRepository extends MongoRepository<Comment, String> {

    Page<Comment> findByPostId(String postId, Pageable pageable);

    @Query("{'author.userId': ?0}")
    List<Comment> findByUserId(Long userId);

    // 自定义扩展方法
    long countByPostId(String postId);
}
