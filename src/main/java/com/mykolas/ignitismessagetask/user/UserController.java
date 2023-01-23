package com.mykolas.ignitismessagetask.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    // Need POST user , need DELETE user, GET stats.

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("getusers")
    public List<User> getUsers() {
        return userService.getAllUsersService();
    }


    // POST new user. Change to RequestUser POJO.
    @ResponseStatus(code = HttpStatus.CREATED, reason = "created")
    @PostMapping("createuser")
    public void createUser(@RequestBody UserAddRequest userAddRequest){
        userService.createUser(userAddRequest);
    }
}
