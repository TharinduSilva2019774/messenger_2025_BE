package com.example.messenger_2025.controller;

import com.example.messenger_2025.payload.*;
import com.example.messenger_2025.service.ChatGPTService;
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

    @Autowired
    private ChatGPTService chatGPTService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello this is Messages endpoint.";
    }

    @GetMapping()
    public GetAllMessagesResponseDto getAllMessages(@RequestParam ("userId") String userId, @RequestParam ("chatId") long chatId) {
        return messageService.getAllMessages(userId,chatId);
    }

    @PostMapping("")
    public PostMessageResponce postMessage(@RequestBody PostMessageDto postMessageDto) {
        return messageService.postMessage(postMessageDto);
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/messages")
    public GetAllMessagesResponseDto handlePostMessage(PostMessageDto postMessageDto) {

        // 1. Save to DB
        messageService.postMessage(postMessageDto);

        // 2. Return to broadcast
        return messageService.getAllMessages(postMessageDto.getClarkId(), postMessageDto.getChatId());
    }

    @MessageMapping("/chat.delete")
    @SendTo("/topic/messages")
    public GetAllMessagesResponseDto handleDeleteMessage(DeleteMessageDto deleteMessageDto) {

        // 1. Save to DB
        GetMessageResponseDto deletedMessage = messageService.deleteMessage(deleteMessageDto);

        // 2. Return to broadcast
        return messageService.getAllMessages(deletedMessage.getClarkId(), deletedMessage.getChatId());
    }

    @MessageMapping("/chat.gpt")
    @SendTo("/topic/messages")
    public GetAllMessagesResponseDto handleChatGPTMessage(GetChatGPTRequestDto getChatGPTRequestDto) {

        // 1. Save to DB
        chatGPTService.chatGPTResponse(getChatGPTRequestDto);

        // 2. Return to broadcast
        return messageService.getAllMessages(getChatGPTRequestDto.getClarkId(), getChatGPTRequestDto.getChatId());
    }

}
