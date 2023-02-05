package com.mykolas.ignitismessagetask.message;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserNotLoggedInException extends RuntimeException{
    public UserNotLoggedInException() {
        super("User is not logged in.");
    }
}
