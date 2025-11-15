package com.example.opdracht_frameworks_spring.dto;

public class CommentPayload {
    private String content;
    private Integer userId;

    public CommentPayload() {}

    public CommentPayload(String content, Integer userId) {
        this.content = content;
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public static class PutCommentPayload {
        private String content;

        public PutCommentPayload() {}

        public PutCommentPayload(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
        public void setContent(String content) {
            this.content = content;
        }
    }
}
