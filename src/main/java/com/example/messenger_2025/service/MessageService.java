package com.example.messenger_2025.service;

import com.example.messenger_2025.model.Message;
import com.example.messenger_2025.model.User;
import com.example.messenger_2025.payload.GetAllMessagesResponseDto;
import com.example.messenger_2025.payload.GetMessageResponseDto;
import com.example.messenger_2025.payload.PostMessageDto;
import com.example.messenger_2025.repository.MessageRepository;
import com.example.messenger_2025.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    public GetAllMessagesResponseDto getAllMessages(String clarkId) throws Exception {
        User user = userService.getUserByClarkId(clarkId);


        List<Message> messages = messageRepository.findAll();

        List<GetMessageResponseDto> getMessageResponseDtos = new ArrayList<>();

        for(Message message : messages){
            GetMessageResponseDto getMessageResponseDto = new GetMessageResponseDto(message.getId(),message.getMessageBody(),message.getTime(),message.getUser().getId(),false);

            if(user.getId() == message.getUser().getId()){
                getMessageResponseDto.setCurrentUser(true);
            }

            getMessageResponseDtos.add(getMessageResponseDto);
        }

        return new GetAllMessagesResponseDto(getMessageResponseDtos);
    }

    public String postMessage(PostMessageDto postMessageDto) throws Exception {
        User user = userService.getUserByClarkId(postMessageDto.getClarkId());

        Message newMessage = new Message();
        newMessage.setMessageBody(postMessageDto.getMessage());
        newMessage.setUser(user);

        messageRepository.save(newMessage);

        return "Saved";
    }
}
