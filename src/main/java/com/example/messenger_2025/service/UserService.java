package com.example.messenger_2025.service;

import com.example.messenger_2025.model.User;
import com.example.messenger_2025.payload.PostUserDto;
import com.example.messenger_2025.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public User getUserByClarkId(String clarkId) throws Exception {
        Optional<User> optionalUser = userRepository.getUserByClarkId(clarkId);
        if (optionalUser.isEmpty()){
            throw new Exception();
        }
        return optionalUser.get();
    }
}
