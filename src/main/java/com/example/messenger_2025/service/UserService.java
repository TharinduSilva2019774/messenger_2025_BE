package com.example.messenger_2025.service;

import com.example.messenger_2025.model.User;
import com.example.messenger_2025.payload.PostUserDto;
import com.example.messenger_2025.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public String postUser(PostUserDto postUserDto){
        User user = new User();
        user.setFirstName(postUserDto.getFirstName());
        user.setLastName(postUserDto.getLastName());
        user.setClarkId(postUserDto.getClarkId());
        userRepository.save(user);
        return "User Saved";
    }
}
