package com.example.messenger_2025.service;

import com.example.messenger_2025.exceptions.ResourceNotFoundException;
import com.example.messenger_2025.model.Chat;
import com.example.messenger_2025.model.Chat_User;
import com.example.messenger_2025.model.User;
import com.example.messenger_2025.payload.*;
import com.example.messenger_2025.repository.ChatRepository;
import com.example.messenger_2025.repository.Chat_UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private KeyService keyService;

    public String addChat(PostChatDto postChatDto){
        Chat chat = new Chat();
        chat.setName(postChatDto.getChatName());
        chatRepository.save(chat);
        return "Successfully added";
    }

    public GetAllChatDto getAllChats(String clarkId) {
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

    public Chat getChat(long id) {
        Optional<Chat> chatOptional = chatRepository.findById(id);
        if (chatOptional.isEmpty()){
            throw new ResourceNotFoundException("Chat not found");
        }
        return chatOptional.get();
    }

    public GetChatDetailsDto getChatDetailsDto(long id) {
        Chat chat = getChat(id);
        List<Chat_User> chatUserList = chatUserRepository.findAllByChat(chat);

        List<GetUserDetailsDto> userDetailsDtoLists = new ArrayList<>();

        String clarkId;
        for (Chat_User chatUser : chatUserList){
            clarkId=chatUser.getUser().getClarkId();
            userDetailsDtoLists.add(new GetUserDetailsDto(chatUser.getUser().getId(),clarkId,keyService.getKey(clarkId).getKey()));
        }

        return new GetChatDetailsDto(chat.getId(),chat.getName(),userDetailsDtoLists);
    }

}
