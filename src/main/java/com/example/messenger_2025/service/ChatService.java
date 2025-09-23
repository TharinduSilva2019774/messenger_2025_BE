package com.example.messenger_2025.service;

import com.example.messenger_2025.model.Chat;
import com.example.messenger_2025.model.Chat_User;
import com.example.messenger_2025.model.User;
import com.example.messenger_2025.payload.GetAllChatDto;
import com.example.messenger_2025.payload.GetChatDto;
import com.example.messenger_2025.payload.PostChatDto;
import com.example.messenger_2025.repository.ChatRepository;
import com.example.messenger_2025.repository.Chat_UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private Chat_UserRepository chatUserRepository;

    public String addChat(PostChatDto postChatDto){
        Chat chat = new Chat();
        chat.setName(postChatDto.getChatName());
        chatRepository.save(chat);
        return "Successfully added";
    }

    public GetAllChatDto getAllChats(String clarkId) throws Exception {
        User user = userService.getUserByClarkId(clarkId);
        List<Chat_User> chatUserList = chatUserRepository.findAllByUser(user);
        List<Chat> chats = chatUserList.stream()
                .map(Chat_User::getChat)
                .toList();

        GetAllChatDto getAllChatDto = new GetAllChatDto();

        for(Chat chat : chats){
            getAllChatDto.getGetChatDtoList().add(new GetChatDto(chat.getId(),chat.getName()));
        }
        return getAllChatDto;
    }

    public Chat getChat(long id) throws Exception {
        Optional<Chat> chatOptional = chatRepository.findById(id);
        if (chatOptional.isEmpty()){
            throw new Exception();
        }
        return chatOptional.get();
    }

}
