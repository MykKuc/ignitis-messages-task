package com.mykolas.ignitismessagetask.user;

import com.mykolas.ignitismessagetask.security.AuthResponse;
import com.mykolas.ignitismessagetask.security.JwtMaker;
import com.mykolas.ignitismessagetask.security.UnauthorizedException;
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

    // Need POST user , need DELETE user, GET stats.

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private final JwtMaker jwtMaker;

    private final UserRepository userRepository;


    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtMaker jwtMaker, UserRepository userRepository){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtMaker = jwtMaker;
        this.userRepository = userRepository;
    }

    @GetMapping("getusers")
    public List<User> getUsers() {
        return userService.getAllUsersService();
    }


    // POST new user. Change to RequestUser POJO. Improve Request User.
    @ResponseStatus(code = HttpStatus.CREATED, reason = "created")
    @PostMapping("create")
    public void createUser(@RequestBody @Valid UserAddRequest userAddRequest){
        userService.createUser(userAddRequest);
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    //++
    @PostMapping("login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginRequest loginRequest) throws Exception {

        Optional<User> currentUser = userRepository.findByEmail(loginRequest.getEmail());
        String roleOfAuthenticatedUser = currentUser.get().getRole();
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
        userService.storeJwt(loginRequest.getEmail(), token);

        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }

    //++
    @GetMapping ("logout")
    public ResponseEntity<String> deleteToken() throws UnauthorizedException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailOfCurrentUser = auth.getName();
        Optional<User> currentUserOptional = userRepository.findByEmail(emailOfCurrentUser);
        User currentUser = currentUserOptional.get();
        userService.deleteToken(currentUser);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //++
    @GetMapping("current")
    public String getCurrentUser () {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
