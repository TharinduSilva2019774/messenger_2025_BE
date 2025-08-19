package com.example.messenger_2025.controller;

import com.example.messenger_2025.payload.PostMessageDto;
import com.example.messenger_2025.payload.PostUserDto;
import com.example.messenger_2025.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public String postUser(@RequestBody PostUserDto postUserDto){
        return userService.postUser(postUserDto);
    }
}
