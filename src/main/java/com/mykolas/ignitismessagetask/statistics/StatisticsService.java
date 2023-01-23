package com.mykolas.ignitismessagetask.statistics;

import com.mykolas.ignitismessagetask.message.MessageRepository;
import com.mykolas.ignitismessagetask.user.User;
import com.mykolas.ignitismessagetask.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService {

    private MessageRepository messageRepository;
    private UserRepository userRepository;

    @Autowired
    public StatisticsService(MessageRepository messageRepository, UserRepository userRepository){
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public List<Statistic> getStatistics(){

        ArrayList<Statistic> allUserStatisticsArray = new ArrayList<>();

        List<User> listOfAllUsers = userRepository.findAll();

        for(User user:listOfAllUsers){

            Statistic userStatistic = new Statistic();
            userStatistic.setUserId(user.getId());
            userStatistic.setUserEmail(user.getEmail());

            allUserStatisticsArray.add(userStatistic);
        }
        return allUserStatisticsArray;
    }
}
