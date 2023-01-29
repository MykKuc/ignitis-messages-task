package com.mykolas.ignitismessagetask.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoSuchEmailOrPasswordException extends RuntimeException{
    public NoSuchEmailOrPasswordException(){
        super("No such Email or Password. ");
    }
}
