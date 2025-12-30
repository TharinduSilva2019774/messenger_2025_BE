package com.example.messenger_2025.service;

import com.example.messenger_2025.exceptions.DuplicateResourceException;
import com.example.messenger_2025.exceptions.ResourceNotFoundException;
import com.example.messenger_2025.model.Key;
import com.example.messenger_2025.model.User;
import com.example.messenger_2025.payload.GetKeyDto;
import com.example.messenger_2025.payload.PostKeyDto;
import com.example.messenger_2025.repository.KeyRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KeyService {

    @Autowired
    private KeyRepository keyRepository;

    @Autowired
    private UserService userService;

    public GetKeyDto getKey(String deviceUID){
        Optional<Key> optKey = keyRepository.findByDeviceUID(deviceUID);
        if (optKey.isEmpty()){
            throw new ResourceNotFoundException("Key not Found");
        }
        Key key = optKey.get();

        return new GetKeyDto(key.getKey(), key.getDeviceUID());
    }

    public String postKey(PostKeyDto postKeyDto){
        User user = userService.getUserByClarkId(postKeyDto.getClarkId());

        Optional<Key> existing = keyRepository.findByDeviceUID(postKeyDto.getDeviceUID());

        if(existing.isPresent()){
            throw new DuplicateResourceException("DeviceUID already exist");
        }

        Key key = new Key();
        key.setKey(postKeyDto.getKey());
        key.setDeviceUID(postKeyDto.getDeviceUID());
        key.setUser(user);

        keyRepository.save(key);

        return "Success";
    }
}
