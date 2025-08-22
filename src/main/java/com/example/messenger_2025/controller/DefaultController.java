package com.example.messenger_2025.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class DefaultController {

    @GetMapping("/")
    public String helloWorld(){
        return "HelloWorld";
    }
}
