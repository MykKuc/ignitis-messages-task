package com.mykolas.ignitismessagetask.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    // Write a new message.
    @PostMapping
    public void createMessage(@RequestBody NewMessageRequest newMessageRequest) {
        messageService.createMessage(newMessageRequest);
    }
}
