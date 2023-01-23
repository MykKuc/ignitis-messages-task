package com.mykolas.ignitismessagetask.message;


import com.mykolas.ignitismessagetask.user.User;
import com.mykolas.ignitismessagetask.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    // Method to get all the messages.
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }



    // Method to create a new message.
    public void createMessage(NewMessageRequest newMessageRequest) {

        // Get author info from logged in information.

        Optional<User> presentReceive = userRepository.findById(newMessageRequest.getReceiverId());
        if(presentReceive.isEmpty()){
            throw new MessageReceiverNotExistException(newMessageRequest.getReceiverId());
        }

        LocalDateTime currentLocalDateTime = LocalDateTime.now();

        Message message = Message.builder()
                .authorId(newMessageRequest.getAuthorId())
                .time(currentLocalDateTime)
                .content(newMessageRequest.getContent())
                .receiverId(newMessageRequest.getReceiverId())
                .build();

        messageRepository.save(message);
    }
}
