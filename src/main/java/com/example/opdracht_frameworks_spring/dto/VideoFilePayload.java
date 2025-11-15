package com.example.opdracht_frameworks_spring.dto;

public class VideoFilePayload {
    private String videoFileUrl;

    // Constructors
    public VideoFilePayload() {}

    public VideoFilePayload(String videoFileUrl) {
        this.videoFileUrl = videoFileUrl;
    }

    // Getters and Setters
    public String getVideoFileUrl() {
        return videoFileUrl;
    }

    public void setVideoFileUrl(String videoFileUrl) {
        this.videoFileUrl = videoFileUrl;
    }
}
