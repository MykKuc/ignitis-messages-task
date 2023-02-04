package com.mykolas.ignitismessagetask.message;


import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Builder
public class Message {

    private Long id;
    private Long authorId;
    private LocalDateTime time;
    private Long length;
    private String content;
    private Long receiverId;
    private boolean authoractive;

    public Message(Long id, Long authorId, LocalDateTime time, Long length, String content, Long receiverId, Boolean authoractive) {
        this.id = id;
        this.authorId = authorId;
        this.time = time;
        this.length = length;
        this.content = content;
        this.receiverId = receiverId;
        this.authoractive = authoractive;
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

    public boolean isAuthoractive() {
        return authoractive;
    }

    public void setAuthoractive(boolean authoractive) {
        this.authoractive = authoractive;
    }
}
