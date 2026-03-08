package com.example.messenger_2025.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetChatGPTRequestDto {

    private String message;

    private String clarkId;

    private long chatId;

    private String otherClarkId;

    private String context;

}
