package com.mykolas.ignitismessagetask.statistics;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class Statistic {

    private Long userId;
    private String userEmail;
    private Integer totalMessages;
    private LocalDateTime firstMessage;
    private LocalDateTime lastMessage;
    private Double averageMessageLength;
    private String lastMessageText;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Statistic(Long userId, String userEmail, Integer totalMessages, LocalDateTime firstMessage, LocalDateTime lastMessage, Double averageMessageLength, String lastMessageText) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.totalMessages = totalMessages;
        this.firstMessage = firstMessage;
        this.lastMessage = lastMessage;
        this.averageMessageLength = averageMessageLength;
        this.lastMessageText = lastMessageText;
    }

    public Statistic() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTotalMessages() {
        return totalMessages;
    }

    public void setTotalMessages(Integer totalMessages) {
        this.totalMessages = totalMessages;
    }

    public LocalDateTime getFirstMessage() {
        return firstMessage;
    }

    public void setFirstMessage(LocalDateTime firstMessage) {
        this.firstMessage = firstMessage;
    }

    public LocalDateTime getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(LocalDateTime lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Double getAverageMessageLength() {
        return averageMessageLength;
    }

    public void setAverageMessageLength(Double averageMessageLength) {
        this.averageMessageLength = averageMessageLength;
    }

    public String getLastMessageText() {
        return lastMessageText;
    }

    public void setLastMessageText(String lastMessageText) {
        this.lastMessageText = lastMessageText;
    }
}
