package com.example.opdracht_frameworks_spring.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "video_file")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VideoFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "video_file_url", nullable = false)
    private String videoFileUrl;

    // Bidirectional relationship back to Vlog
    @OneToOne(mappedBy = "videoFile")
    @JsonIgnore
    private Vlog vlog;

    // Constructors
    public VideoFile() {}

    public VideoFile(String videoFileUrl) {
        this.videoFileUrl = videoFileUrl;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer videoFileId) {
        this.id = videoFileId;
    }

    public String getVideoFileUrl() {
        return videoFileUrl;
    }

    public void setVideoFileUrl(String videoFileUrl) {
        this.videoFileUrl = videoFileUrl;
    }

    public Vlog getVlog() {
        return vlog;
    }

    public void setVlog(Vlog vlog) {
        this.vlog = vlog;
    }
}