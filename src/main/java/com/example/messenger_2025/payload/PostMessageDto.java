package com.example.messenger_2025.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostMessageDto {

    private String message;
    private String clarkId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClarkId() {
        return clarkId;
    }

    public void setClarkId(String clarkId) {
        this.clarkId = clarkId;
    }
}
