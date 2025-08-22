package com.example.messenger_2025.controller;

import com.example.messenger_2025.payload.GetAllMessagesResponseDto;
import com.example.messenger_2025.payload.PostMessageDto;
import com.example.messenger_2025.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello this is Messages endpoint";
    }

    @GetMapping("/{id}")
    public GetAllMessagesResponseDto getAllMessages(@PathVariable("id") String id) throws Exception {
        return messageService.getAllMessages(id);
    }

    @PostMapping("")
    public String postMessage(@RequestBody PostMessageDto postMessageDto) throws Exception {
        return messageService.postMessage(postMessageDto);
    }
}
