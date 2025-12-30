package com.example.messenger_2025.controller;

import com.example.messenger_2025.payload.GetKeyDto;
import com.example.messenger_2025.payload.PostKeyDto;
import com.example.messenger_2025.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/key")
public class KeyController {

    @Autowired
    private KeyService keyService;

    @GetMapping("")
    public GetKeyDto getKeyFromDeviceId(@RequestParam("deviceUID") String deviceUID) {
        return keyService.getKey(deviceUID);
    }

    @PostMapping("")
    public String postKey(@RequestBody PostKeyDto postKeyDto) {
        return keyService.postKey(postKeyDto);
    }

}
