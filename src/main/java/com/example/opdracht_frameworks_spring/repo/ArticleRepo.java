package com.example.opdracht_frameworks_spring.repo;

import com.example.opdracht_frameworks_spring.data.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Integer> {
    Optional<Article> findBySlugContainingIgnoreCase(String slug);

    @Query("SELECT a FROM Article a JOIN a.tags t WHERE LOWER(t) LIKE LOWER(CONCAT('%', :tag, '%'))")
    List<Article> findByTagsContainingIgnoreCase(@Param("tag") String tag);

    List<Article> findAllByOrderByPublishedAtDesc();
}