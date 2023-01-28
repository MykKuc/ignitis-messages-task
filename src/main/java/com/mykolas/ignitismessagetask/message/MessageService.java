package com.mykolas.ignitismessagetask.message;


import com.mykolas.ignitismessagetask.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageQueries messageQueries;

    public MessageService(MessageQueries messageQueries){
        this.messageQueries = messageQueries;
    }

    public List<Message> getAllMessages() {
        return messageQueries.selectAllMessagesQuery();
    }

    // GOOD TO HERE.
    public void createMessage(NewMessageRequest newMessageRequest) {

        User presentReceiver = messageQueries.fetchUserById(newMessageRequest.getReceiverId());
        if(presentReceiver == null){
            throw new MessageReceiverNotExistException(newMessageRequest.getReceiverId());
        }

        // Check if receiver is not an ADMIN.
        String presentReceiverRole = presentReceiver.getRole();
        if(presentReceiverRole.equals("ROLE_ADMIN")){
            throw new ReceiverIsAdminException();
        }

        Authentication currentUserAuthentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = currentUserAuthentication.getName();
        User currentUser = messageQueries.fetchUserByEmail(currentUserEmail);
        Long currentUserId = currentUser.getId();

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

        messageQueries.insertNewMessageIntoMessagesTable(message);
    }
}
