package com.example.opdracht_frameworks_spring.repo;

import com.example.opdracht_frameworks_spring.data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByArticleId(Integer articleId);
}
