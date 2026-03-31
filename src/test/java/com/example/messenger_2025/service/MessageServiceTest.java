package com.example.messenger_2025.service;

import com.example.messenger_2025.exceptions.ResourceNotFoundException;
import com.example.messenger_2025.model.Chat;
import com.example.messenger_2025.model.Message;
import com.example.messenger_2025.model.User;
import com.example.messenger_2025.payload.*;
import com.example.messenger_2025.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserService userService;

    @Mock
    private ChatService chatService;

    @InjectMocks
    private MessageService messageService;

    @Test
    void getAllMessages() {
        // Arrange
        String clarkId = "clark123";
        long chatId = 1L;

        Chat chat = new Chat();
        chat.setId(chatId);

        User mUser = new User();
        mUser.setId(10L);
        mUser.setClarkId("clark123");
        mUser.setFirstName("John");

        User encUser = new User();
        encUser.setId(20L);
        encUser.setClarkId("encClark");

        Message msg1 = new Message();
        msg1.setId(100L);
        msg1.setMessageBody("Message 1");
        msg1.setTime(new Timestamp(System.currentTimeMillis()));
        msg1.setUser(mUser);
        msg1.setChat(chat);
        msg1.setEncUser(encUser);
        msg1.setIsEncrypted(false);

        Message msg2 = new Message();
        msg2.setId(101L);
        msg2.setMessageBody("Message 2");
        msg2.setTime(new Timestamp(System.currentTimeMillis()));
        msg2.setUser(mUser);
        msg2.setChat(chat);
        msg2.setEncUser(encUser);
        msg2.setIsEncrypted(true);

        List<Message> messages = new ArrayList<>();
        messages.add(msg1);
        messages.add(msg2);

        when(chatService.getChat(chatId)).thenReturn(chat);
        when(messageRepository.findTop20ByChatOrderByIdDesc(chat)).thenReturn(messages);

        // Act
        GetAllMessagesResponseDto response = messageService.getAllMessages(chatId);

        // Assert
        assertNotNull(response);
        verify(chatService, times(1)).getChat(chatId);
        verify(messageRepository, times(1)).findTop20ByChatOrderByIdDesc(chat);
    }

    @Test
    void postMessage() {
        // Arrange
        PostMessageDto postDto = new PostMessageDto("Hello","clark123",1L,"encClark",true);

        User user = new User();
        user.setId(10L);
        user.setClarkId("clark123");
        user.setFirstName("John");

        User encUser = new User();
        encUser.setId(20L);
        encUser.setClarkId("encClark");

        Chat chat = new Chat();
        chat.setId(1L);

        Message savedMessage = new Message();
        savedMessage.setId(100L);
        savedMessage.setMessageBody("Hello");
        savedMessage.setTime(new Timestamp(System.currentTimeMillis()));
        savedMessage.setUser(user);
        savedMessage.setChat(chat);
        savedMessage.setEncUser(encUser);
        savedMessage.setIsEncrypted(true);

        when(userService.getUserByClarkId("clark123")).thenReturn(user);
        when(userService.getUserByClarkId("encClark")).thenReturn(encUser);
        when(chatService.getChat(1L)).thenReturn(chat);
        
        when(messageRepository.save(any(Message.class))).thenReturn(savedMessage);

        // Act
        PostMessageResponce response = messageService.postMessage(postDto);

        // Assert
        assertNotNull(response);
        
        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
        verify(messageRepository, times(1)).save(messageCaptor.capture());
        
        Message capturedMsg = messageCaptor.getValue();
        assertEquals("Hello", capturedMsg.getMessageBody());
        assertEquals(user, capturedMsg.getUser());
        assertEquals(encUser, capturedMsg.getEncUser());
        assertEquals(chat, capturedMsg.getChat());
        assertTrue(capturedMsg.getIsEncrypted());
    }

    @Test
    void deleteMessage() {
        // Arrange
        DeleteMessageDto deleteDto = new DeleteMessageDto();
        deleteDto.setMessageId(100L);

        User mUser = new User();
        mUser.setId(10L);
        mUser.setClarkId("clark123");
        mUser.setFirstName("John");

        User encUser = new User();
        encUser.setId(20L);
        encUser.setClarkId("encClark");

        Chat chat = new Chat();
        chat.setId(1L);

        Message message = new Message();
        message.setId(100L);
        message.setMessageBody("To delete");
        message.setTime(new Timestamp(System.currentTimeMillis()));
        message.setUser(mUser);
        message.setChat(chat);
        message.setEncUser(encUser);
        message.setIsEncrypted(false);

        when(messageRepository.findById(100L)).thenReturn(Optional.of(message));

        // Act
        GetMessageResponseDto response = messageService.deleteMessage(deleteDto);

        // Assert
        assertNotNull(response);
        verify(messageRepository, times(1)).findById(100L);
        verify(messageRepository, times(1)).delete(message);
    }

    @Test
    void testDeleteMessage_MessageDoesNotExist_ThrowsResourceNotFoundException() {
        // Arrange
        DeleteMessageDto deleteDto = new DeleteMessageDto();
        deleteDto.setMessageId(100L);

        when(messageRepository.findById(100L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> messageService.deleteMessage(deleteDto));

        assertEquals("Message not found to delete", exception.getMessage());
        verify(messageRepository, times(1)).findById(100L);
        verify(messageRepository, never()).delete(any());
    }
}