package com.mykolas.ignitismessagetask.message;


import com.mykolas.ignitismessagetask.user.User;
import com.mykolas.ignitismessagetask.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        Optional<User> presentReceiverOptional = userRepository.findById(newMessageRequest.getReceiverId());
        if(presentReceiverOptional.isEmpty()){
            throw new MessageReceiverNotExistException(newMessageRequest.getReceiverId());
        }

        // Check if receiver is not an ADMIN.
        String presentReceiverRole = presentReceiverOptional.get().getRole();
        if(presentReceiverRole.equals("ROLE_ADMIN")){
            throw new ReceiverIsAdminException();
        }

        Authentication currentUserAuthentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = currentUserAuthentication.getName();
        Optional<User> currentUserOptional = userRepository.findByEmail(currentUserEmail);
        Long currentUserId = currentUserOptional.get().getId();

        if (currentUserId == newMessageRequest.getReceiverId()){
            throw new MessageAuthorAndReceiverSameException();
        }

        LocalDateTime currentLocalDateTime = LocalDateTime.now();

        int lengthOfTheMessage = newMessageRequest.getContent().length();
        Long convertedLengthOfMessageToLong = (long) lengthOfTheMessage;


        Message message = Message.builder()
                .authorId(currentUserId)
                .time(currentLocalDateTime)
                .content(newMessageRequest.getContent())
                .receiverId(newMessageRequest.getReceiverId())
                .length(convertedLengthOfMessageToLong)
                .build();

        messageRepository.save(message);
    }
}
