package com.example.messenger_2025.payload;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class PostKeyDto {

    @NonNull
    private String key;

    @NonNull
    private String deviceUID;

    @NonNull
    private String clarkId;

}
