package com.mykolas.ignitismessagetask.statistics;

import com.mykolas.ignitismessagetask.jooqdatabase.Tables;
import com.mykolas.ignitismessagetask.jooqdatabase.tables.Messages;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Component
public class StatisticsQueries {

    @Autowired
    private DSLContext create;

    public Integer fetchTotalNumberMessagesByAuthor(Integer authorId) {
        return create
                .fetchCount(Tables.MESSAGES, Messages.MESSAGES.AUTHOR_ID.eq(authorId));
    }

    public Integer fetchTotalMessagesByReceiver(Integer receiverId){
        return create
                .fetchCount(Tables.MESSAGES, Messages.MESSAGES.RECEIVER_ID.eq(receiverId));
    }

    // Fix this. Maybe I should provide just String, don't need anything else.
    public LocalDateTime fetchTimeFirstMessageOfUserById(Long userId) {
       return Objects.requireNonNull(create
               .select(DSL.min(Messages.MESSAGES.TIME))
               .from(Tables.MESSAGES)
               .where(Messages.MESSAGES.AUTHOR_ID.eq(Math.toIntExact(userId)))
               .fetchOne()).value1();
    }

    public LocalDateTime fetchTimeLatestMessageOfUserById(Long userId) {
        return Objects.requireNonNull(create
                .select(DSL.max(Messages.MESSAGES.TIME))
                .from(Tables.MESSAGES)
                .where(Messages.MESSAGES.AUTHOR_ID.eq(Math.toIntExact(userId)))
                .fetchOne()).value1();
    }


    public Double fetchAverageLengthOfMessageByUser(Integer userId){

       BigDecimal avgValue =  create
                .select(DSL.avg(Messages.MESSAGES.LENGTH))
                .from(Tables.MESSAGES)
                .where(Messages.MESSAGES.AUTHOR_ID.eq(userId))
               .fetchSingle().value1();

       if(avgValue != null){
           return avgValue.doubleValue();
       } else  {
           return 0.0;
       }
    }

    public String fetchLastMessageTextByUserById(Long userId, LocalDateTime latestMessageTime) {
       return Objects.requireNonNull(create
               .select(Messages.MESSAGES.CONTENT)
               .from(Tables.MESSAGES)
               .where(Messages.MESSAGES.AUTHOR_ID.eq(Math.toIntExact(userId)).and(Messages.MESSAGES.TIME.eq(latestMessageTime)))
               .fetchOne()).value1();

    }

}
