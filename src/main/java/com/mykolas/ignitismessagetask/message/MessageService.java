package com.mykolas.ignitismessagetask.message;


import com.mykolas.ignitismessagetask.user.User;
import com.mykolas.ignitismessagetask.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        int lengthOfTheMessage = newMessageRequest.getContent().length();
        Long convertedLengthOfMessageToLong = (long) lengthOfTheMessage;

        Message message = Message.builder()
                .authorId(newMessageRequest.getAuthorId())
                .time(currentLocalDateTime)
                .content(newMessageRequest.getContent())
                .receiverId(newMessageRequest.getReceiverId())
                .length(convertedLengthOfMessageToLong)
                .build();

        messageRepository.save(message);
    }
}
