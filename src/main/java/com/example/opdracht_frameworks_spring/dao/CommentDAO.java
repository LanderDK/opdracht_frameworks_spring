package com.example.opdracht_frameworks_spring.dao;

import com.example.opdracht_frameworks_spring.data.entity.Comment;
import com.example.opdracht_frameworks_spring.repo.CommentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentDAO {
    private final CommentRepo repo;

    public CommentDAO(CommentRepo repo) {
        this.repo = repo;
    }

    public List<Comment> findAllByArticleId(Integer articleId) {
        return repo.findAllByArticleId(articleId);
    }

    public Comment save(Comment comment) {
        return repo.save(comment);
    }

    public Optional<Comment> update(Comment comment) {
        return repo.findById(comment.getId()).map(existingComment -> {
            existingComment.setContent(comment.getContent());
            return repo.save(existingComment);
        });
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
