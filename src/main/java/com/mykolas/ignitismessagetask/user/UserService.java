package com.mykolas.ignitismessagetask.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mykolas.ignitismessagetask.user.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsersService() {
       return userRepository.findAll();
    }

    // Create User service. Check if same email does not exist.
    public void createUser(UserAddRequest userAddRequest){
        Optional<User> sameUser = userRepository.findByEmail(userAddRequest.getEmail());
        if(sameUser.isPresent()){
            throw new UserAlreadyExistsException(userAddRequest.getEmail());
        }

        User user = User.builder()
                .name(userAddRequest.getName())
                .email(userAddRequest.getEmail())
                .password(userAddRequest.getPassword())
                .build();

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
