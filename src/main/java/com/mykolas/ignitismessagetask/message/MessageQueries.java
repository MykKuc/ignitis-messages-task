package com.mykolas.ignitismessagetask.message;

import com.mykolas.ignitismessagetask.jooqdatabase.Tables;
import com.mykolas.ignitismessagetask.jooqdatabase.tables.Messages;
import com.mykolas.ignitismessagetask.jooqdatabase.tables.Users;
import com.mykolas.ignitismessagetask.jooqdatabase.tables.records.MessagesRecord;
import com.mykolas.ignitismessagetask.user.User;
import org.jooq.*;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageQueries {

    @Autowired
    private DSLContext create;

    public Record1<Long> fetchRecordOfIdOfCurrentlyLoggedinUserByEmail(String currentlyLoggedInUserEmail){
        return create
                .select(Users.USERS.ID)
                .from(Tables.USERS)
                .where(Users.USERS.EMAIL.eq(currentlyLoggedInUserEmail))
                .fetchOne();
    }

    public List<Message> fetchAllMessagesWhereCurrentUserIsEitherMessageAuthorOrMessageReceiver(Long idOfCurrentUser){
        return create
                .selectFrom(Tables.MESSAGES)
                .where(Messages.MESSAGES.AUTHOR_ID.eq(idOfCurrentUser).or(Messages.MESSAGES.RECEIVER_ID.eq(idOfCurrentUser)))
                .fetch().into(Message.class);
    }

    public List<Message> selectAllMessagesQuery() {
        Result<Record> results = create.select().from(Tables.MESSAGES).fetch();
        return results.into(Message.class);
    }

    public Record fetchUserById (Long id){
        return create.select().from(Tables.USERS)
                .where(Users.USERS.ID.eq(id))
                .fetchOne();
    }

    public User fetchUserByEmail (String email){
        Record result = create.select().from(Tables.USERS)
                .where(Users.USERS.EMAIL.eq(email))
                .fetchOne();
        return result.into(User.class);
    }

    public void insertNewMessageIntoMessagesTable(Message newMessage) {
        create
                .insertInto(Tables.MESSAGES,Messages.MESSAGES.AUTHOR_ID,Messages.MESSAGES.TIME,Messages.MESSAGES.CONTENT,Messages.MESSAGES.RECEIVER_ID,Messages.MESSAGES.LENGTH)
                .values(newMessage.getAuthorId(),newMessage.getTime(),newMessage.getContent(), newMessage.getReceiverId(), Math.toIntExact(newMessage.getLength()))
                .execute();
    }
}
