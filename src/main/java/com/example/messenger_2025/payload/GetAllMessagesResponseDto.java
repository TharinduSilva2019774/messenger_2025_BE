package com.example.messenger_2025.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GetAllMessagesResponseDto {

    public GetAllMessagesResponseDto() {
    }

    public GetAllMessagesResponseDto(List<GetMessageResponseDto> messageResponses) {
        this.messageResponses = messageResponses;
    }

    List<GetMessageResponseDto> messageResponses;

    public List<GetMessageResponseDto> getMessageResponses() {
        return messageResponses;
    }

    public void setMessageResponses(List<GetMessageResponseDto> messageResponses) {
        this.messageResponses = messageResponses;
    }
}
