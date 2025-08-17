package com.example.messenger_2025.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("/")
    public String helloWorld(){
        return "HelloWorld";
    }
}
