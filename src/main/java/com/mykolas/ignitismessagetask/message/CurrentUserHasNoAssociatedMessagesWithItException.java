package com.mykolas.ignitismessagetask.message;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CurrentUserHasNoAssociatedMessagesWithItException extends RuntimeException{
    public CurrentUserHasNoAssociatedMessagesWithItException(){
        super("Currently logged in user is neither author or receiver of any messages. ");
    }
}
