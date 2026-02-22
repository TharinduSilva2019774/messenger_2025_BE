package com.example.messenger_2025.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class GetMessageResponseDto {

    private long id;

    private String message;

    private Timestamp time;

    private long userId;

    private String clarkId;

    private String userFirstName;

    private long chatId;

    private long encUserId;

    private String encClarkId;

    private boolean isEncrypted;

}
