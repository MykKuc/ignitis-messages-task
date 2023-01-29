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

    // TODO Add more fields to Statistic about the received messages.
    public List<Statistic> getStatistics(){

        ArrayList<Statistic> allUserStatisticsArray = new ArrayList<>();

        List<User> listOfAllUsers = userQueries.getAllExistingUsers();

        for(User user:listOfAllUsers){

            Statistic userStatistic = new Statistic();

            userStatistic.setUserId(user.getId());
            userStatistic.setUserEmail(user.getEmail());

            userStatistic.setTotalMessagesSent(statisticsQueries.fetchTotalNumberMessagesByAuthor(Math.toIntExact(user.getId())));
            userStatistic.setTotalMessagesReceived(statisticsQueries.fetchTotalMessagesByReceiver(Math.toIntExact(user.getId())));

            //Earliest message time.
           userStatistic.setFirstMessageSentTime(statisticsQueries.fetchTimeFirstMessageOfUserById(user.getId()));
            // Last message time.
            LocalDateTime latestMessageTime = statisticsQueries.fetchTimeLatestMessageOfUserById(user.getId());
           userStatistic.setLastMessageSentTime(latestMessageTime);
            // Get Average length of message.
           userStatistic.setAverageMessageLength(statisticsQueries.fetchAverageLengthOfMessageByUser(Math.toIntExact(user.getId())));

           // Last message text.
            if(latestMessageTime != null){
                userStatistic.setLastMessageSentText(statisticsQueries.fetchLastMessageTextByUserById(user.getId(),latestMessageTime));
            }


            allUserStatisticsArray.add(userStatistic);
        }


        return allUserStatisticsArray;
    }
}
