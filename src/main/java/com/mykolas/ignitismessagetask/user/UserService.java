package com.mykolas.ignitismessagetask.user;

import com.mykolas.ignitismessagetask.security.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserQueries userQueries;

    public UserService(UserQueries userQueries){
        this.userQueries = userQueries;
    }

    // GOOD UP TO HERE.
    public List<User> getAllUsersService() {
       return userQueries.getAllExistingUsers();
    }

    // Create User service. Check if same email does not exist.
    public void createUser(UserAddRequest userAddRequest){

        boolean isUserWithProvidedEmailExists = userQueries.isUserWithProvidedEmailAlreadyExists(userAddRequest.getEmail());
        if(isUserWithProvidedEmailExists){
            throw new UserAlreadyExistsException(userAddRequest.getEmail());
        }

        User user = User.builder()
                .name(userAddRequest.getName())
                .email(userAddRequest.getEmail())
                .password(userAddRequest.getPassword())
                .role("ROLE_USER")
                .build();

        userQueries.insertNewUserIntoUsersTable(user);
    }

    public void deleteUser(Long id) {
        userQueries.markUserIsDeleteByIdAndMarkMessagesOfDeletedAuthor(id);
    }

    // Method to store JWT.
    //++
    public void storeJwt(String email, String token) throws Exception{

        User userResult = userQueries.fetchUserByEmail(email);
        if(userResult == null){
            throw new UnauthorizedException("User does not exist. ");
        }

        userQueries.updateTokenValueForUserByEmail(token,email);
    }

    public void deleteTokenAndClearSecurityContext(String emailOfCurrentUser){
        SecurityContextHolder.clearContext();
        userQueries.updateUserJwtTokenToNullValue(emailOfCurrentUser);
    }

}
