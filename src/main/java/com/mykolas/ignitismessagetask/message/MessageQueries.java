package com.mykolas.ignitismessagetask.message;

import com.mykolas.ignitismessagetask.jooqdatabase.Tables;
import com.mykolas.ignitismessagetask.jooqdatabase.tables.Messages;
import com.mykolas.ignitismessagetask.jooqdatabase.tables.Users;
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

    public List<Message> selectAllMessagesQuery() {
        Result<Record> results = create.select().from(Tables.MESSAGES).fetch();
        return results.into(Message.class);
    }

    public Record fetchUserById (Long id){
        Integer idConvertedToInteger = Math.toIntExact(id);
        return create.select().from(Tables.USERS)
                .where(Users.USERS.ID.eq(idConvertedToInteger))
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
                .values(Math.toIntExact(newMessage.getAuthorId()),newMessage.getTime(),newMessage.getContent(), Math.toIntExact(newMessage.getReceiverId()), Math.toIntExact(newMessage.getLength()))
                .execute();
    }
}
