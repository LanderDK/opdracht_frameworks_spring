package com.example.opdracht_frameworks_spring.dto;

import java.util.Date;
import java.util.List;

public class VlogPayload {
    private String title;
    private String excerpt;
    private String content;
    private String slug;
    private List<String> tags;
    private VideoFilePayload videoFile;
    private List<Integer> userIds;

    // Constructors
    public VlogPayload() {}

    public VlogPayload(String title, String excerpt, String content, String slug,
                       List<String> tags, VideoFilePayload videoFile, List<Integer> userIds) {
        this.title = title;
        this.excerpt = excerpt;
        this.content = content;
        this.slug = slug;
        this.tags = tags;
        this.videoFile = videoFile;
        this.userIds = userIds;
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

    public VideoFilePayload getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(VideoFilePayload videoFile) {
        this.videoFile = videoFile;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
}