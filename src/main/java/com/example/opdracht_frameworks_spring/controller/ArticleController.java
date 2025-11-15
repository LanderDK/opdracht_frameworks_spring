package com.example.opdracht_frameworks_spring.controller;

import com.example.opdracht_frameworks_spring.dao.ArticleDAO;
import com.example.opdracht_frameworks_spring.data.entity.Article;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleDAO dao;

    public ArticleController(ArticleDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Article> getArticles() {
        return dao.findAll();
    }

    @GetMapping("{id}")
    public Article getArticleById(@PathVariable("id") Integer id) {
        return dao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found"));
    }

    @GetMapping("slug/{slug}")
    public Article getArticleById(@PathVariable("slug") String slug) {
        return dao.findBySlug(slug).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found"));
    }
}
