package com.example.messenger_2025.controller;

import com.example.messenger_2025.payload.GetAllMessagesResponseDto;
import com.example.messenger_2025.payload.PostMessageDto;
import com.example.messenger_2025.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello this is Messages endpoint";
    }

    @GetMapping("")
    public GetAllMessagesResponseDto getAllMessages(){
        return messageService.getAllMessages();
    }

    @PostMapping("")
    public String postMessage(@RequestBody PostMessageDto postMessageDto){
        return messageService.postMessage(postMessageDto);
    }
}
