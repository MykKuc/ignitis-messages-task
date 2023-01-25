package com.mykolas.ignitismessagetask.message;


import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Builder
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "author_id")
    private Long authorId;
    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "length")
    private Long length;

    @Column(name = "content")
    private String content;

    @Column(name = "receiver_id")
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
