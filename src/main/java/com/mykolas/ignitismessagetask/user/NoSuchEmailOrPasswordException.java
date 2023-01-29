package com.mykolas.ignitismessagetask.user;

public class NoSuchEmailOrPasswordException extends RuntimeException{

    public NoSuchEmailOrPasswordException(){
        super("No such Email or Password. ");
    }
}
