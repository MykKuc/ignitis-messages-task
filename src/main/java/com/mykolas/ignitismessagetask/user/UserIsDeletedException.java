package com.mykolas.ignitismessagetask.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserIsDeletedException extends RuntimeException{
    public UserIsDeletedException(String email) {
        super("User " + email + " has been deleted.");
    }
}
