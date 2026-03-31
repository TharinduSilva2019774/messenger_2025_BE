package com.example.messenger_2025.service;

import com.example.messenger_2025.exceptions.ResourceNotFoundException;
import com.example.messenger_2025.model.Chat;
import com.example.messenger_2025.model.Message;
import com.example.messenger_2025.model.User;
import com.example.messenger_2025.payload.*;
import com.example.messenger_2025.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;


    public GetAllMessagesResponseDto getAllMessages(long chatId) {

        Chat chat = chatService.getChat(chatId);

        List<Message> messages = messageRepository.findTop20ByChatOrderByIdDesc(chat);
        Collections.reverse(messages);

        List<GetMessageResponseDto> getMessageResponseDtos = new ArrayList<>();
        User mUser;
        for(Message message : messages){
            mUser = message.getUser();
            GetMessageResponseDto getMessageResponseDto = new GetMessageResponseDto(
                    message.getId(),
                    message.getMessageBody(),
                    message.getTime(),
                    mUser.getId(),
                    mUser.getClarkId(),
                    mUser.getFirstName(),
                    message.getChat().getId(),
                    message.getEncUser().getId(),
                    message.getEncUser().getClarkId(),
                    message.getIsEncrypted(),
                    message.getIsRead());

            getMessageResponseDtos.add(getMessageResponseDto);
        }

        return new GetAllMessagesResponseDto(getMessageResponseDtos);
    }

    public PostMessageResponce postMessage(PostMessageDto postMessageDto) {
        User user = userService.getUserByClarkId(postMessageDto.getClarkId());
        User encUser = userService.getUserByClarkId(postMessageDto.getEncClarkId());

        Message newMessage = new Message();
        newMessage.setMessageBody(postMessageDto.getMessage());
        newMessage.setUser(user);
        newMessage.setChat(chatService.getChat(postMessageDto.getChatId()));
        newMessage.setEncUser(encUser);
        newMessage.setIsEncrypted(postMessageDto.isEncrypted());
        Message savedMessage = messageRepository.save(newMessage);
        User savedMessageUser = savedMessage.getUser();
        return new PostMessageResponce(
                savedMessage.getId(),
                savedMessage.getMessageBody(),
                savedMessage.getTime(),
                savedMessageUser.getId(),
                savedMessageUser.getClarkId(),
                savedMessageUser.getFirstName(),
                savedMessage.getChat().getId(),
                savedMessage.getEncUser().getId(),
                savedMessage.getEncUser().getClarkId(),
                savedMessage.getIsEncrypted());
    }

    public GetMessageResponseDto deleteMessage(DeleteMessageDto deleteMessageDto) {

        Optional<Message> messageToDel = messageRepository.findById(deleteMessageDto.getMessageId());

        if (messageToDel.isEmpty()){
            throw new ResourceNotFoundException("Message not found to delete");
        }
        Message message = messageToDel.get();

        messageRepository.delete(message);

        return new GetMessageResponseDto(
                message.getId(),
                message.getMessageBody(),
                message.getTime(),
                message.getUser().getId(),
                message.getUser().getClarkId(),
                message.getUser().getFirstName(),
                message.getChat().getId(),
                message.getEncUser().getId(),
                message.getEncUser().getClarkId(),
                message.getIsEncrypted(),
                message.getIsRead());
    }

    @Transactional
    public void updateIsRead(PostUpdateIsRead postUpdateIsRead){
        List<Message> messageList = messageRepository.findAllById(postUpdateIsRead.getMessageList());

        for (Message message : messageList){
            message.setIsRead(postUpdateIsRead.isNewStatus());
            message.setSetReadAt(Timestamp.valueOf(LocalDateTime.now()));
        }

    }
}
