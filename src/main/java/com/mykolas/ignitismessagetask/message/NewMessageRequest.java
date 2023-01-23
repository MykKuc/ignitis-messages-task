package com.mykolas.ignitismessagetask.message;

public class NewMessageRequest {

    private Long authorId;

    private String content;

    private Long receiverId;

    public NewMessageRequest(Long authorId, String content, Long receiverId) {
        this.authorId = authorId;
        this.content = content;
        this.receiverId = receiverId;
    }

    public NewMessageRequest() {
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
}
