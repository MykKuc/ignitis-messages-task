package com.mykolas.ignitismessagetask.user;

import com.mykolas.ignitismessagetask.jooqdatabase.Tables;
import com.mykolas.ignitismessagetask.jooqdatabase.tables.Messages;
import com.mykolas.ignitismessagetask.jooqdatabase.tables.Users;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQueries {

    @Autowired
    private DSLContext create;

    public List<User> getAllExistingUsers() {
      Result<Record> allUsersSelected = create.select().from(Tables.USERS).fetch();
      return allUsersSelected.into(User.class);
    }

    public User fetchUserByEmail(String email) {
       return create.select()
                .from(Tables.USERS)
                .where(Users.USERS.EMAIL.eq(email))
                .fetchOne()
                .into(User.class);
    }

    public boolean isUserWithProvidedEmailAlreadyExists(String email) {
        return create
                .fetchExists(
                        create.selectFrom(Tables.USERS)
                                .where(Users.USERS.EMAIL
                                        .eq(email)
                                ));
    }

    public void insertNewUserIntoUsersTable(User newUser) {
        create
                .insertInto(Tables.USERS,Users.USERS.NAME,Users.USERS.EMAIL,Users.USERS.PASSWORD,Users.USERS.ROLE)
                .values(newUser.getName(),newUser.getEmail(),newUser.getPassword(), newUser.getRole())
                .execute();
    }

    public void markUserIsDeleteByIdAndMarkMessagesOfDeletedAuthor(Long id) {
        create
                .update(Tables.USERS)
                .set(Users.USERS.ISACTIVE, false)
                .where(Users.USERS.ID.eq(Math.toIntExact(id)))
                .execute();
        create
                .update(Tables.MESSAGES)
                .set(Messages.MESSAGES.AUTHORACTIVE, false)
                .where(Messages.MESSAGES.AUTHOR_ID.eq(Math.toIntExact(id)))
                .execute();
    }

    public void updateTokenValueForUserByEmail(String token, String email) {
        create
                .update(Tables.USERS)
                .set(Users.USERS.TOKEN, token)
                .where(Users.USERS.EMAIL.eq(email))
                .execute();
    }

    public void updateUserJwtTokenToNullValue(String userEmail) {
        create
                .update(Tables.USERS)
                .setNull(Users.USERS.TOKEN)
                .where(Users.USERS.EMAIL.eq(userEmail))
                .execute();
    }
}
