package com.mykolas.ignitismessagetask.message;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Method to get all the messages.
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }



    // Method to create a new message.
    public void createMessage(NewMessageRequest newMessageRequest) {


        Message message = Message.builder()
                .authorId(newMessageRequest.getAuthorId())
                .content(newMessageRequest.getContent())
                .receiverId(newMessageRequest.getReceiverId())
                .build();

        messageRepository.save(message);
    }
}
