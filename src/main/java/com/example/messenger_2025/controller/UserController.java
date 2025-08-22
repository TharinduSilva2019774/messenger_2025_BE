package com.example.messenger_2025.controller;

import com.example.messenger_2025.payload.PostUserDto;
import com.example.messenger_2025.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
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
