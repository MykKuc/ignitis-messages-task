package com.mykolas.ignitismessagetask.message;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MessageReceiverNotExistException extends RuntimeException {

    public MessageReceiverNotExistException(Long id){
        super("Receiver with such Id: " + id + " does not exist.");
    }
}
