package com.example.opdracht_frameworks_spring.dao;

import com.example.opdracht_frameworks_spring.data.entity.Article;
import com.example.opdracht_frameworks_spring.data.entity.Comment;
import com.example.opdracht_frameworks_spring.data.entity.User;
import com.example.opdracht_frameworks_spring.dto.CommentPayload;
import com.example.opdracht_frameworks_spring.repo.ArticleRepo;
import com.example.opdracht_frameworks_spring.repo.CommentRepo;
import com.example.opdracht_frameworks_spring.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentDAO {
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;
    private final ArticleRepo articleRepo;

    public CommentDAO(CommentRepo commentRepo, UserRepo userRepo, ArticleRepo articleRepo) {
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
        this.articleRepo = articleRepo;
    }

    public List<Comment> findAllByArticleId(Integer articleId) {
        return commentRepo.findAllByArticleId(articleId);
    }

    public Comment save(Integer articleId, CommentPayload payload) {
        Comment comment = new Comment();
        comment.setContent(payload.getContent());
        comment.setPublishedAt(new Date());

        User user = userRepo.findById(payload.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + payload.getUserId()));
        Article article = articleRepo.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article ID: " + articleId));

        comment.setUser(user);
        comment.setArticle(article);

        return commentRepo.save(comment);
    }

    public Optional<Comment> update(Integer articleId, Integer commentId, CommentPayload.PutCommentPayload payload) {
        return commentRepo.findByIdAndArticleId(commentId, articleId).map(existingComment -> {
            existingComment.setContent(payload.getContent());
            return commentRepo.save(existingComment);
        });
    }

    public void deleteById(Integer articleId, Integer commentId) {
        Comment comment = commentRepo.findByIdAndArticleId(commentId, articleId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with ID: " + commentId + " for Article ID: " + articleId));
        commentRepo.deleteById(comment.getId());
    }
}
