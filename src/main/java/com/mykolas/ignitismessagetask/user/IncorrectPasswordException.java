package com.mykolas.ignitismessagetask.user;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException() {
        super("Incorrect password provided. ");
    }
}
