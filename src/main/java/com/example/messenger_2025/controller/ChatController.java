package com.example.messenger_2025.controller;

import com.example.messenger_2025.payload.GetAllChatDto;
import com.example.messenger_2025.payload.PostChatDto;
import com.example.messenger_2025.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("")
    public String postChat(@RequestBody PostChatDto postChatDto){
        return chatService.addChat(postChatDto);
    }

    @GetMapping("")
    public GetAllChatDto getAllChats(@RequestParam ("id") String id) throws Exception {
        return chatService.getAllChats(id);
    }

}
