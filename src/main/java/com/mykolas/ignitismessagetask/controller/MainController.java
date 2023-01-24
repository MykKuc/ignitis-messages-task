package com.mykolas.ignitismessagetask.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("test")
    public String getText() {
        return "Testing if this works.";
    }

    @GetMapping("/login/auth")
    public String getTextIfLoggedIn() {
        return "Authentication is working.";
    }
}
