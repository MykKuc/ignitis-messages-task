package com.mykolas.ignitismessagetask.message;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MessageAuthorAndReceiverSameException extends RuntimeException{

    public MessageAuthorAndReceiverSameException() {
        super(" Message Author and Receiver are the same entity. ");
    }
}
