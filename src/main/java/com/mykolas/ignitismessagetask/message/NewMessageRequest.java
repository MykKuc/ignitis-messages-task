package com.mykolas.ignitismessagetask.message;

public class NewMessageRequest {


    private String content;

    private Long receiverId;

    public NewMessageRequest(String content, Long receiverId) {
        this.content = content;
        this.receiverId = receiverId;
    }

    public NewMessageRequest() {
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
