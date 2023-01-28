package com.mykolas.ignitismessagetask.message;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class Message {

    private Long id;
    private Long authorId;
    private LocalDateTime time;
    private Long length;
    private String content;
    private Long receiverId;

    public Message(Long id, Long authorId, LocalDateTime time, Long length, String content, Long receiverId) {
        this.id = id;
        this.authorId = authorId;
        this.time = time;
        this.length = length;
        this.content = content;
        this.receiverId = receiverId;
    }

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
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
