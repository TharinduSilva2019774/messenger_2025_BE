package com.example.messenger_2025.controller;

import com.example.messenger_2025.payload.GetAllMessagesResponseDto;
import com.example.messenger_2025.payload.PostMessageDto;
import com.example.messenger_2025.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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

    @GetMapping()
    public GetAllMessagesResponseDto getAllMessages(@RequestParam ("userId") String userId, @RequestParam ("chatId") long chatId) throws Exception {
        return messageService.getAllMessages(userId,chatId);
    }

    @PostMapping("")
    public String postMessage(@RequestBody PostMessageDto postMessageDto) throws Exception {
        return messageService.postMessage(postMessageDto);
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/messages")
    public GetAllMessagesResponseDto handleMessage(PostMessageDto postMessageDto) throws Exception {

        // 1. Save to DB
        messageService.postMessage(postMessageDto);

        // 2. Return to broadcast
        return messageService.getAllMessages(postMessageDto.getClarkId(), postMessageDto.getChatId());
    }
}
