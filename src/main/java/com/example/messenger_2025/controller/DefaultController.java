package com.example.messenger_2025.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("/")
    public String helloWorld(){
        return "HelloWorld G CLOUD";
    }

    @GetMapping("/health")
    public String helloWorldHealth(@AuthenticationPrincipal Jwt jwt){
        return "HelloWorld This is the "+jwt;
    }
}
