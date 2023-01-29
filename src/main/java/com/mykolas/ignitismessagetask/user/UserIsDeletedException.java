package com.mykolas.ignitismessagetask.user;

public class UserIsDeletedException extends RuntimeException{
    public UserIsDeletedException(String email) {
        super("User " + email + " has been deleted.");
    }
}
