package com.example.opdracht_frameworks_spring.dao;

import com.example.opdracht_frameworks_spring.data.entity.Article;
import com.example.opdracht_frameworks_spring.repo.ArticleRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleDAO {
    private final ArticleRepo repo;

    public ArticleDAO(ArticleRepo repo) {
        this.repo = repo;
    }

    public List<Article> findAll() {
        return repo.findAll();
    }

    public Optional<Article> findById(Integer id) {
        return repo.findById(id);
    }

    public Optional<Article> findBySlug(String slug) {
        return repo.findBySlugContainingIgnoreCase(slug);
    }

    public List<Article> findByTag(String tag) {
        return repo.findByTagsContainingIgnoreCase(tag);
    }
}