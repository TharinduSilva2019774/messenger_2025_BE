package com.example.messenger_2025.service;

import com.example.messenger_2025.model.Chat;
import com.example.messenger_2025.payload.PostChatDto;
import com.example.messenger_2025.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public String addChat(PostChatDto postChatDto){
        Chat chat = new Chat();
        chat.setName(postChatDto.getChatName());
        chatRepository.save(chat);
        return "Successfully added";
    }

}
