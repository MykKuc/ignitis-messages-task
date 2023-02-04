package com.mykolas.ignitismessagetask.statistics;

import com.mykolas.ignitismessagetask.user.User;
import com.mykolas.ignitismessagetask.user.UserQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService {

    private final UserQueries userQueries;

    private final StatisticsQueries statisticsQueries;

    @Autowired
    public StatisticsService(UserQueries userQueries, StatisticsQueries statisticsQueries) {
        this.userQueries = userQueries;
        this.statisticsQueries = statisticsQueries;
    }

    public List<Statistic> getStatistics(){

        ArrayList<Statistic> allUserStatisticsArray = new ArrayList<>();

        List<User> listOfAllUsers = userQueries.getAllExistingUsers();

        for(User user:listOfAllUsers){

            Statistic userStatistic = new Statistic();

            userStatistic.setUserId(user.getId());
            userStatistic.setUserEmail(user.getEmail());

            userStatistic.setTotalMessagesSent(statisticsQueries.fetchTotalNumberMessagesByAuthor(user.getId()));
            userStatistic.setTotalMessagesReceived(statisticsQueries.fetchTotalMessagesByReceiver(user.getId()));

            userStatistic.setFirstMessageSentTime(statisticsQueries.fetchTimeFirstMessageByAuthorId(user.getId()));
            userStatistic.setFirstMessageReceivedTime(statisticsQueries.fetchTimeFirstMessageByReceiverId(user.getId()));

            LocalDateTime latestMessageTime = statisticsQueries.fetchTimeLatestMessageByAuthorId(user.getId());
            userStatistic.setLastMessageSentTime(latestMessageTime);

            LocalDateTime latestReceivedMessageTime = statisticsQueries.fetchTimeLatestMessageByReceiverId(user.getId());
            userStatistic.setLastMessageReceivedTime(latestReceivedMessageTime);

            userStatistic.setAverageSentMessageLength(statisticsQueries.fetchAverageLengthOfMessageByAuthorId(user.getId()));
            userStatistic.setAverageReceivedMessageLength(statisticsQueries.fetchAverageLengthOfMessageByReceiverId(user.getId()));

            if(latestMessageTime != null){
                userStatistic.setLastMessageSentText(statisticsQueries.fetchLastMessageTextByAuthorId(user.getId(),latestMessageTime));
            }

            if(latestReceivedMessageTime != null){
                userStatistic.setLastMessageReceivedText(statisticsQueries.fetchLastMessageTextByReceiverId(user.getId(),latestReceivedMessageTime));
            }

            allUserStatisticsArray.add(userStatistic);
        }


        return allUserStatisticsArray;
    }
}
