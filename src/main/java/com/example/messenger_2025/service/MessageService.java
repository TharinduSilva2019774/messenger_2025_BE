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
    private UserRepository userRepository;

    public GetAllMessagesResponseDto getAllMessages(){
        List<Message> messages = messageRepository.findAll();

        List<GetMessageResponseDto> getMessageResponseDtos = new ArrayList<>();

        for(Message message : messages){

            GetMessageResponseDto getMessageResponseDto = new GetMessageResponseDto(message.getId(),message.getMessageBody(),message.getTime(),message.getUser().getId());
            getMessageResponseDtos.add(getMessageResponseDto);
        }

        return new GetAllMessagesResponseDto(getMessageResponseDtos);
    }

    public String postMessage(PostMessageDto postMessageDto){
        Optional<User> optionalUser = userRepository.getUserByClarkId(postMessageDto.getClarkId());

        if(optionalUser.isEmpty()){
            return "No clark user found";
        }

        Message newMessage = new Message();
        newMessage.setMessageBody(postMessageDto.getMessage());
        newMessage.setUser(optionalUser.get());

        messageRepository.save(newMessage);

        return "Saved";
    }
}
