package com.example.messenger_2025.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostMessageDto {

    private String message;
    private String clarkId;
    private long chatId;
    private String encClarkId;

}
