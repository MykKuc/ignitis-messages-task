package com.mykolas.ignitismessagetask.message;


import com.mykolas.ignitismessagetask.user.User;
import org.jooq.Record;
import org.jooq.Record1;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class MessageService {

    private final MessageQueries messageQueries;

    public MessageService(MessageQueries messageQueries) {
        this.messageQueries = messageQueries;
    }

    public List<Message> getAllMessages() {

        Authentication currentUserAuthentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = currentUserAuthentication.getName();
        Record1<Long> recordOfidOfCurrentUser = messageQueries.fetchRecordOfIdOfCurrentlyLoggedinUserByEmail(currentEmail);

        if (Objects.isNull(recordOfidOfCurrentUser)) {
            throw new UserNotLoggedInException();
        }

        Long idOfCurrentlyLoggedInUser = recordOfidOfCurrentUser.value1();

        List<Message> allMessagesByCurrentlyLoggedInUser = messageQueries.fetchAllMessagesWhereCurrentUserIsEitherMessageAuthorOrMessageReceiver(idOfCurrentlyLoggedInUser);
        if (allMessagesByCurrentlyLoggedInUser.isEmpty()){
            throw new CurrentUserHasNoAssociatedMessagesWithItException();
        }

        return allMessagesByCurrentlyLoggedInUser;
    }

    public void createMessage(NewMessageRequest newMessageRequest) {

        Record presentReceiverRecord = messageQueries.fetchUserById(newMessageRequest.getReceiverId());
        if (Objects.isNull(presentReceiverRecord)) {
            throw new MessageReceiverNotExistException(newMessageRequest.getReceiverId());
        }

        // Check if receiver is not an ADMIN.
        String presentReceiverRole = presentReceiverRecord.into(User.class).getRole();
        if (presentReceiverRole.equals("ROLE_ADMIN")) {
            throw new ReceiverIsAdminException();
        }

        Authentication currentUserAuthentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = currentUserAuthentication.getName();
        User currentUser = messageQueries.fetchUserByEmail(currentUserEmail);
        Long currentUserId = currentUser.getId();

        if (currentUserId == newMessageRequest.getReceiverId()) {
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
