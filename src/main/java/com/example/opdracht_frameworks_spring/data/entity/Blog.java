package com.example.opdracht_frameworks_spring.data.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("BLOG")
public class Blog extends Article {

    private Integer readTime; // in minutes

    public Blog() {
        super();
    }

    public Integer calculateReadTime() {
        if (getContent() == null || getContent().isEmpty()) {
            return 0;
        }
        String[] words = getContent().trim().split("\\s+");
        int wordCount = words.length;
        return Math.max(1, wordCount / 200); // assuming average reading speed of 200 wpm
    }

    public Blog(String title, String excerpt, String content, String slug,
                List<String> tags, Date publishedAt, Date updatedAt, Integer readTime) {
        super(title, excerpt, content, slug, tags, publishedAt, updatedAt);
        this.readTime = readTime;
    }

    public Integer getReadTime() {
        return readTime;
    }

    public void setReadTime(Integer readTime) {
        this.readTime = readTime;
    }
}