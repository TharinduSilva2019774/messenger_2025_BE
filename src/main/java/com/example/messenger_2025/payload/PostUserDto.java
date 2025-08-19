package com.example.messenger_2025.payload;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class PostUserDto {

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String clarkId;

}
