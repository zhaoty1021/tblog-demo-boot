package com.tyrone.blog.repository.mongo;

import com.tyrone.blog.domain.pojo.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    // 正确使用 Spring Data 的 Page
    Page<Comment> findByPostId(String postId, Pageable pageable);

    @Query("{'author.userId': ?0}")
    List<Comment> findByUserId(Long userId);

    long countByPostId(String postId);
}
