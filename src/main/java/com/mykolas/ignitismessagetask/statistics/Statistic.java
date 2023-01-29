package com.mykolas.ignitismessagetask.statistics;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class Statistic {

    private Long userId;
    private String userEmail;
    private Integer totalMessagesSent;
    private Integer totalMessagesReceived;
    private LocalDateTime firstMessageSentTime;
    private LocalDateTime lastMessageSentTime;
    private Double averageMessageLength;
    private String lastMessageSentText;


    public Statistic(Long userId, String userEmail, Integer totalMessagesSent, Integer totalMessagesReceived, LocalDateTime firstMessageSentTime, LocalDateTime lastMessageSentTime, Double averageMessageLength, String lastMessageSentText) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.totalMessagesSent = totalMessagesSent;
        this.firstMessageSentTime = firstMessageSentTime;
        this.totalMessagesReceived = totalMessagesReceived;
        this.lastMessageSentTime = lastMessageSentTime;
        this.averageMessageLength = averageMessageLength;
        this.lastMessageSentText = lastMessageSentText;
    }

    public Statistic() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTotalMessagesSent() {
        return totalMessagesSent;
    }

    public void setTotalMessagesSent(Integer totalMessagesSent) {
        this.totalMessagesSent = totalMessagesSent;
    }

    public Integer getTotalMessagesReceived() {
        return totalMessagesReceived;
    }

    public void setTotalMessagesReceived(Integer totalMessagesReceived) {
        this.totalMessagesReceived = totalMessagesReceived;
    }

    public LocalDateTime getFirstMessageSentTime() {
        return firstMessageSentTime;
    }

    public void setFirstMessageSentTime(LocalDateTime firstMessageSentTime) {
        this.firstMessageSentTime = firstMessageSentTime;
    }

    public LocalDateTime getLastMessageSentTime() {
        return lastMessageSentTime;
    }

    public void setLastMessageSentTime(LocalDateTime lastMessageSentTime) {
        this.lastMessageSentTime = lastMessageSentTime;
    }

    public Double getAverageMessageLength() {
        return averageMessageLength;
    }

    public void setAverageMessageLength(Double averageMessageLength) {
        this.averageMessageLength = averageMessageLength;
    }

    public String getLastMessageSentText() {
        return lastMessageSentText;
    }

    public void setLastMessageSentText(String lastMessageSentText) {
        this.lastMessageSentText = lastMessageSentText;
    }
}
