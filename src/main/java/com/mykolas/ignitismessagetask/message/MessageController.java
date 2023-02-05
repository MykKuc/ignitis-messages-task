package com.mykolas.ignitismessagetask.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getAllSpecificUserMessages() {
        return messageService.getAllMessages();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMessage(@RequestBody @Valid NewMessageRequest newMessageRequest) {
        messageService.createMessage(newMessageRequest);
    }
}
