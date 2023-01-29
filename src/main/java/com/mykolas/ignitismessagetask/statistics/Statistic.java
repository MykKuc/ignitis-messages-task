package com.mykolas.ignitismessagetask.statistics;

import lombok.Builder;

import java.time.LocalDateTime;

public class Statistic {

    private Long userId;
    private String userEmail;
    private Integer totalMessagesSent;
    private Integer totalMessagesReceived;
    private LocalDateTime firstMessageSentTime;
    // New
    private LocalDateTime firstMessageReceivedTime;
    private LocalDateTime lastMessageSentTime;
    // New
    private LocalDateTime lastMessageReceivedTime;
    private Double averageSentMessageLength;
    // New
    private Double averageReceivedMessageLength;
    private String lastMessageSentText;
    // New
    private String lastMessageReceivedText;


    public Statistic(Long userId, String userEmail, Integer totalMessagesSent, Integer totalMessagesReceived, LocalDateTime firstMessageSentTime, LocalDateTime lastMessageSentTime, Double averageSentMessageLength, String lastMessageSentText, LocalDateTime firstMessageReceivedTime, LocalDateTime lastMessageReceivedTime,Double averageReceivedMessageLength, String lastMessageReceivedText ) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.totalMessagesSent = totalMessagesSent;
        this.firstMessageSentTime = firstMessageSentTime;
        this.totalMessagesReceived = totalMessagesReceived;
        this.lastMessageSentTime = lastMessageSentTime;
        this.averageSentMessageLength = averageSentMessageLength;
        this.lastMessageSentText = lastMessageSentText;
        this.firstMessageReceivedTime = firstMessageReceivedTime;
        this.lastMessageReceivedTime = lastMessageReceivedTime;
        this.averageReceivedMessageLength = averageReceivedMessageLength;
        this.lastMessageReceivedText = lastMessageReceivedText;
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

    public Double getAverageSentMessageLength() {
        return averageSentMessageLength;
    }

    public void setAverageSentMessageLength(Double averageSentMessageLength) {
        this.averageSentMessageLength = averageSentMessageLength;
    }

    public String getLastMessageSentText() {
        return lastMessageSentText;
    }

    public void setLastMessageSentText(String lastMessageSentText) {
        this.lastMessageSentText = lastMessageSentText;
    }

    public LocalDateTime getFirstMessageReceivedTime() {
        return firstMessageReceivedTime;
    }

    public void setFirstMessageReceivedTime(LocalDateTime firstMessageReceivedTime) {
        this.firstMessageReceivedTime = firstMessageReceivedTime;
    }

    public LocalDateTime getLastMessageReceivedTime() {
        return lastMessageReceivedTime;
    }

    public void setLastMessageReceivedTime(LocalDateTime lastMessageReceivedTime) {
        this.lastMessageReceivedTime = lastMessageReceivedTime;
    }

    public Double getAverageReceivedMessageLength() {
        return averageReceivedMessageLength;
    }

    public void setAverageReceivedMessageLength(Double averageReceivedMessageLength) {
        this.averageReceivedMessageLength = averageReceivedMessageLength;
    }

    public String getLastMessageReceivedText() {
        return lastMessageReceivedText;
    }

    public void setLastMessageReceivedText(String lastMessageReceivedText) {
        this.lastMessageReceivedText = lastMessageReceivedText;
    }
}
