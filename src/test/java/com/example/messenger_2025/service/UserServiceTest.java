package com.example.messenger_2025.service;

import com.example.messenger_2025.exceptions.DuplicateResourceException;
import com.example.messenger_2025.exceptions.ResourceNotFoundException;
import com.example.messenger_2025.model.User;
import com.example.messenger_2025.payload.PostUserDto;
import com.example.messenger_2025.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserByClarkId_UserExists_ReturnsUser() throws ResourceNotFoundException {
        // Arrange
        String clarkId = "clark123";
        User user = new User();
        user.setClarkId(clarkId);
        when(userRepository.getUserByClarkId(clarkId)).thenReturn(List.of(user));

        // Act
        User result = userService.getUserByClarkId(clarkId);

        // Assert
        assertNotNull(result);
        assertEquals(clarkId, result.getClarkId());
        verify(userRepository, times(1)).getUserByClarkId(clarkId);
    }

    @Test
    void testGetUserByClarkId_UserDoesNotExist_ThrowsResourceNotFoundException() {
        // Arrange
        String clarkId = "clark123";
        when(userRepository.getUserByClarkId(clarkId)).thenReturn(Collections.emptyList());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, 
                () -> userService.getUserByClarkId(clarkId));
        assertEquals("User do not exist", exception.getMessage());
        verify(userRepository, times(1)).getUserByClarkId(clarkId);
    }

    @Test
    void testGetUserByClarkId_MultipleUsersExist_ThrowsDuplicateResourceException() {
        // Arrange
        String clarkId = "clark123";
        User user1 = new User();
        User user2 = new User();
        when(userRepository.getUserByClarkId(clarkId)).thenReturn(List.of(user1, user2));

        // Act & Assert
        DuplicateResourceException exception = assertThrows(DuplicateResourceException.class, 
                () -> userService.getUserByClarkId(clarkId));
        assertEquals("Multiple users exist please report to development", exception.getMessage());
        verify(userRepository, times(1)).getUserByClarkId(clarkId);
    }

    @Test
    void testPostUser_NewUser_SavesAndReturnsMessage() throws Exception {
        // Arrange
        PostUserDto dto = new PostUserDto();
        dto.setClarkId("clark123");
        dto.setFirstName("John");
        dto.setLastName("Doe");

        when(userRepository.getUserByClarkId("clark123")).thenReturn(Collections.emptyList());

        // Act
        String result = userService.postUser(dto);

        // Assert
        assertEquals("User Saved", result);
        
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());
        
        User savedUser = userCaptor.getValue();
        assertEquals("clark123", savedUser.getClarkId());
        assertEquals("John", savedUser.getFirstName());
        assertEquals("Doe", savedUser.getLastName());
    }

    @Test
    void testPostUser_UserAlreadyExists_ThrowsDuplicateResourceException() {
        // Arrange
        PostUserDto dto = new PostUserDto();
        dto.setClarkId("clark123");

        User existingUser = new User();
        existingUser.setClarkId("clark123");
        
        when(userRepository.getUserByClarkId("clark123")).thenReturn(List.of(existingUser));

        // Act & Assert
        DuplicateResourceException exception = assertThrows(DuplicateResourceException.class, 
                () -> userService.postUser(dto));
        assertEquals("user already exist", exception.getMessage());
        
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testPostUser_MultipleUsersExist_ThrowsDuplicateResourceException() {
        // Arrange
        PostUserDto dto = new PostUserDto();
        dto.setClarkId("clark123");

        User existingUser1 = new User();
        User existingUser2 = new User();
        
        when(userRepository.getUserByClarkId("clark123")).thenReturn(List.of(existingUser1, existingUser2));

        // Act & Assert
        DuplicateResourceException exception = assertThrows(DuplicateResourceException.class, 
                () -> userService.postUser(dto));
        assertEquals("Multiple users exist please report to development", exception.getMessage());
        
        verify(userRepository, never()).save(any(User.class));
    }
}
