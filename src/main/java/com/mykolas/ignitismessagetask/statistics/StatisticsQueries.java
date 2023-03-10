package com.mykolas.ignitismessagetask.statistics;

import com.mykolas.ignitismessagetask.jooqdatabase.Tables;
import com.mykolas.ignitismessagetask.jooqdatabase.tables.Messages;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class StatisticsQueries {

    private final double DEFAULT_VALUE_IF_NO_AVERAGE_OF_MESSAGE_LENGTH = 0.0;

    @Autowired
    private DSLContext create;

    public Integer fetchTotalNumberMessagesByAuthor(Long authorId) {
        return create
                .fetchCount(Tables.MESSAGES, Messages.MESSAGES.AUTHOR_ID.eq(authorId));
    }

    public Integer fetchTotalMessagesByReceiver(Long receiverId){
        return create
                .fetchCount(Tables.MESSAGES, Messages.MESSAGES.RECEIVER_ID.eq(receiverId));
    }

    public LocalDateTime fetchTimeFirstMessageByAuthorId(Long authorId) {
       return Objects.requireNonNull(create
               .select(DSL.min(Messages.MESSAGES.TIME))
               .from(Tables.MESSAGES)
               .where(Messages.MESSAGES.AUTHOR_ID.eq(authorId))
               .fetchOne()).value1();
    }

    public LocalDateTime fetchTimeLatestMessageByAuthorId(Long userId) {
        return Objects.requireNonNull(create
                .select(DSL.max(Messages.MESSAGES.TIME))
                .from(Tables.MESSAGES)
                .where(Messages.MESSAGES.AUTHOR_ID.eq(userId))
                .fetchOne()).value1();
    }


    public Double fetchAverageLengthOfMessageByAuthorId(Long userId){

       BigDecimal avgValue =  create
                .select(DSL.avg(Messages.MESSAGES.LENGTH))
                .from(Tables.MESSAGES)
                .where(Messages.MESSAGES.AUTHOR_ID.eq(userId))
               .fetchSingle().value1();

       if(avgValue != null){
           return avgValue.doubleValue();
       } else  {
           return DEFAULT_VALUE_IF_NO_AVERAGE_OF_MESSAGE_LENGTH;
       }
    }

    public String fetchLastMessageTextByAuthorId(Long userId, LocalDateTime latestMessageTime) {
       return Objects.requireNonNull(create
               .select(Messages.MESSAGES.CONTENT)
               .from(Tables.MESSAGES)
               .where(Messages.MESSAGES.AUTHOR_ID.eq(userId).and(Messages.MESSAGES.TIME.eq(latestMessageTime)))
               .fetchOne()).value1();

    }

    public LocalDateTime fetchTimeFirstMessageByReceiverId(Long receiverId) {
        return Objects.requireNonNull(create
                .select(DSL.min(Messages.MESSAGES.TIME))
                .from(Tables.MESSAGES)
                .where(Messages.MESSAGES.RECEIVER_ID.eq(receiverId))
                .fetchOne()).value1();
    }

    public LocalDateTime fetchTimeLatestMessageByReceiverId(Long receiverId) {
        return Objects.requireNonNull(create
                .select(DSL.max(Messages.MESSAGES.TIME))
                .from(Tables.MESSAGES)
                .where(Messages.MESSAGES.RECEIVER_ID.eq(receiverId))
                .fetchOne()).value1();
    }

    public Double fetchAverageLengthOfMessageByReceiverId(Long receiverId){

        BigDecimal avgValue =  create
                .select(DSL.avg(Messages.MESSAGES.LENGTH))
                .from(Tables.MESSAGES)
                .where(Messages.MESSAGES.RECEIVER_ID.eq(receiverId))
                .fetchSingle().value1();

        if(avgValue != null){
            return avgValue.doubleValue();
        } else  {
            return DEFAULT_VALUE_IF_NO_AVERAGE_OF_MESSAGE_LENGTH;
        }
    }

    public String fetchLastMessageTextByReceiverId(Long receiverId, LocalDateTime latestMessageTime) {
        return Objects.requireNonNull(create
                .select(Messages.MESSAGES.CONTENT)
                .from(Tables.MESSAGES)
                .where(Messages.MESSAGES.RECEIVER_ID.eq(receiverId).and(Messages.MESSAGES.TIME.eq(latestMessageTime)))
                .fetchOne()).value1();
    }

}
