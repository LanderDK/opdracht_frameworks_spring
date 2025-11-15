package com.example.opdracht_frameworks_spring.data.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("VLOG")
public class Vlog extends Article {

    @Column(name = "video_file_id")
    private Integer videoFileId;

    // One-to-One relationship with VideoFile
    // Lazy loading by default, CASCADE on delete
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "video_file_id", referencedColumnName = "id",
            insertable = false, updatable = false)
    private VideoFile videoFile;

    // Constructors
    public Vlog() {
        super();
    }

    public Vlog(String title, String excerpt, String content, String slug,
                List<String> tags, Date publishedAt, Date updatedAt,
                VideoFile videoFile) {
        super(title, excerpt, content, slug, tags, publishedAt, updatedAt);
        this.videoFile = videoFile;
    }

    // Getters and Setters
    public Integer getVideoFileId() {
        return videoFileId;
    }

    public void setVideoFileId(Integer videoFileId) {
        this.videoFileId = videoFileId;
    }

    public VideoFile getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(VideoFile videoFile) {
        this.videoFile = videoFile;
        if (videoFile != null) {
            this.videoFileId = videoFile.getId();
        }
    }
}