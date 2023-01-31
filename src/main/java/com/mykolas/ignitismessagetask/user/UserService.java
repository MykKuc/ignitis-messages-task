package com.mykolas.ignitismessagetask.user;

import com.mykolas.ignitismessagetask.security.JwtMaker;
import com.mykolas.ignitismessagetask.security.UnauthorizedException;
import org.jooq.Record;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserQueries userQueries;
    private final AuthenticationManager authenticationManager;
    private final JwtMaker jwtMaker;


    public UserService(UserQueries userQueries, AuthenticationManager authenticationManager, JwtMaker jwtMaker){
        this.userQueries = userQueries;
        this.authenticationManager = authenticationManager;
        this.jwtMaker = jwtMaker;
    }

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public List<User> getAllUsersService() {
       return userQueries.getAllExistingUsers();
    }

    public String loginService(LoginRequest loginRequest) throws Exception {

        Record userByEmailOrNull = userQueries.fetchUserRecordOrNullValueByEmail(loginRequest.getEmail());
        if (Objects.isNull(userByEmailOrNull)){
            throw new NoSuchEmailOrPasswordException();
        }

        User currentUser = userQueries.fetchUserByEmail(loginRequest.getEmail());

        if (Boolean.FALSE.equals(currentUser.getActive())) {
            throw new UserIsDeletedException(loginRequest.getEmail());
        }

        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(),currentUser.getPassword())){
            throw new IncorrectPasswordException();
        }

        String roleOfAuthenticatedUser = currentUser.getRole();
        ArrayList<String> collectionOfRoles = new ArrayList<>();
        collectionOfRoles.add(roleOfAuthenticatedUser);


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword(),
                        collectionOfRoles.stream().map(authority -> (GrantedAuthority) () -> authority)
                                .collect(Collectors.toList())
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtMaker.generateToken(authentication);
        storeJwt(loginRequest.getEmail(), token);
        return token;
    }

    public void createUser(UserAddRequest userAddRequest){

        boolean isUserWithProvidedEmailExists = userQueries.isUserWithProvidedEmailAlreadyExists(userAddRequest.getEmail());
        if(isUserWithProvidedEmailExists){
            throw new UserAlreadyExistsException(userAddRequest.getEmail());
        }

        User user = User.builder()
                .name(userAddRequest.getName())
                .email(userAddRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(userAddRequest.getPassword()))
                .role("ROLE_USER")
                .build();

        userQueries.insertNewUserIntoUsersTable(user);
    }

    public void deleteUser(Long id) {

        User deletableUser = userQueries.fetchUserById(id);

        if (deletableUser.getRole().equals("ROLE_ADMIN")){
            throw new AdminDeleteItselfException();
        }

        userQueries.markUserIsDeleteByIdAndMarkMessagesOfDeletedAuthor(id);
    }

    // Method to store JWT.
    //++
    public void storeJwt(String email, String token) throws Exception{

        User userResult = userQueries.fetchUserByEmail(email);
        if(Objects.isNull(userResult)){
            throw new UnauthorizedException("User does not exist. ");
        }

        userQueries.updateTokenValueForUserByEmail(token,email);
    }

    public void deleteTokenAndClearSecurityContext(String emailOfCurrentUser){
        SecurityContextHolder.clearContext();
        userQueries.updateUserJwtTokenToNullValue(emailOfCurrentUser);
    }

}
