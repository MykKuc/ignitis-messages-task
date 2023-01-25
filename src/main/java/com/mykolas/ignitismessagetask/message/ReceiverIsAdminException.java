package com.mykolas.ignitismessagetask.message;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReceiverIsAdminException extends RuntimeException {

    public ReceiverIsAdminException() {
        super("Admin is not allowed to receive messages.");
    }
}
