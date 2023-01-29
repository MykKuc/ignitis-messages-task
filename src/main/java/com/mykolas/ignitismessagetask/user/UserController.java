package com.mykolas.ignitismessagetask.user;

import com.mykolas.ignitismessagetask.security.AuthResponse;
import com.mykolas.ignitismessagetask.security.JwtMaker;
import com.mykolas.ignitismessagetask.security.UnauthorizedException;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final UserQueries userQueries;

    @Autowired
    public UserController(UserService userService, UserQueries userQueries){
        this.userService = userService;
        this.userQueries = userQueries;
    }

    @GetMapping("getusers")
    public List<User> getUsers() {
        return userService.getAllUsersService();
    }

    @ResponseStatus(code = HttpStatus.CREATED, reason = "created")
    @PostMapping("create")
    public void createUser(@RequestBody @Valid UserAddRequest userAddRequest){
        userService.createUser(userAddRequest);
    }

    @GetMapping("delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // TODO Makes this controller thinner. Too much code in here.
    @PostMapping("login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginRequest loginRequest) throws Exception {
       String returnedToken = userService.loginService(loginRequest);
        return new ResponseEntity<>(new AuthResponse(returnedToken), HttpStatus.OK);
    }

    //++
    @GetMapping ("logout")
    public ResponseEntity<String> deleteToken() throws UnauthorizedException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailOfCurrentUser = auth.getName();
        userService.deleteTokenAndClearSecurityContext(emailOfCurrentUser);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // THis is good.
    @GetMapping("current")
    public String getCurrentUser () {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
