package com.example.messenger_2025.service;

import com.example.messenger_2025.exceptions.DuplicateResourceException;
import com.example.messenger_2025.exceptions.ResourceNotFoundException;
import com.example.messenger_2025.model.User;
import com.example.messenger_2025.payload.PostUserDto;
import com.example.messenger_2025.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public String postUser(PostUserDto postUserDto) throws DuplicateResourceException {
        try{
            getUserByClarkId(postUserDto.getClarkId());
            throw new DuplicateResourceException("User already exist");
        } catch (Exception e) {
            User user = new User();
            user.setFirstName(postUserDto.getFirstName());
            user.setLastName(postUserDto.getLastName());
            user.setClarkId(postUserDto.getClarkId());
            userRepository.save(user);
            return "User Saved";
        }
    }

    public User getUserByClarkId(String clarkId) throws ResourceNotFoundException {
        List<User> userList = userRepository.getUserByClarkId(clarkId);
        if (userList.isEmpty()){
            throw new ResourceNotFoundException("User do not exist");
        } else if (userList.size()>1) {
            throw new DuplicateResourceException("Multiple users exist please report to development");
        } else {
            return userList.getFirst();
        }
    }
}
