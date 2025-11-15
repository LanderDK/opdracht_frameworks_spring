package com.example.opdracht_frameworks_spring.dto;

import java.util.Date;
import java.util.List;

public class VlogPayload {
    private String title;
    private String excerpt;
    private String content;
    private String slug;
    private List<String> tags;
    private Date publishedAt;
    private Date updatedAt;
    private VideoFilePayload videoFile;

    // Constructors
    public VlogPayload() {}

    public VlogPayload(String title, String excerpt, String content, String slug,
                       List<String> tags, Date publishedAt, Date updatedAt, VideoFilePayload videoFile) {
        this.title = title;
        this.excerpt = excerpt;
        this.content = content;
        this.slug = slug;
        this.tags = tags;
        this.publishedAt = publishedAt;
        this.updatedAt = updatedAt;
        this.videoFile = videoFile;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public VideoFilePayload getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(VideoFilePayload videoFile) {
        this.videoFile = videoFile;
    }
}