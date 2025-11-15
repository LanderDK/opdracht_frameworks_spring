package com.example.opdracht_frameworks_spring.controller;

import com.example.opdracht_frameworks_spring.dao.CommentDAO;
import com.example.opdracht_frameworks_spring.data.entity.Comment;
import com.example.opdracht_frameworks_spring.dto.CommentPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentDAO dao;

    public CommentController(CommentDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/articles/{articleId}/comments")
    public List<Comment> getCommentsByArticleId(@PathVariable("articleId") Integer articleId) {
        return dao.findAllByArticleId(articleId);
    }

    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<Void> createComment(@PathVariable("articleId") Integer articleId, @RequestBody CommentPayload payload) {
        Comment savedComment = dao.save(articleId, payload);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedComment.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/articles/{articleId}/comments/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable("articleId") Integer articleId, @PathVariable("commentId") Integer commentId, @RequestBody CommentPayload.PutCommentPayload payload) {
        return dao.update(articleId, commentId, payload)
                .map(_ -> new ResponseEntity<>("", HttpStatus.NO_CONTENT))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));
    }

    @DeleteMapping("/articles/{articleId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("articleId") Integer articleId, @PathVariable("commentId") Integer commentId) {
        dao.deleteById(articleId, commentId);
    }
}
