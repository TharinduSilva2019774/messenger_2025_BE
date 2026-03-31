package com.example.messenger_2025.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostUpdateIsRead {

    private List<Long> messageList;

    private boolean newStatus;

    private long chatId;

}

