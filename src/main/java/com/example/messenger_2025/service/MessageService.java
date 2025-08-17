package com.example.messenger_2025.service;

import com.example.messenger_2025.model.Message;
import com.example.messenger_2025.payload.GetAllMessagesResponseDto;
import com.example.messenger_2025.payload.GetMessageResponseDto;
import com.example.messenger_2025.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public GetAllMessagesResponseDto getAllMessages(){
        List<Message> messages = messageRepository.findAll();

        List<GetMessageResponseDto> getMessageResponseDtos = new ArrayList<>();

        for(Message message : messages){

            GetMessageResponseDto getMessageResponseDto = new GetMessageResponseDto(message.getId(),message.getMessageBody(),message.getTime(),message.getUser().getId());
            getMessageResponseDtos.add(getMessageResponseDto);
        }

        return new GetAllMessagesResponseDto(getMessageResponseDtos);
    }
}
