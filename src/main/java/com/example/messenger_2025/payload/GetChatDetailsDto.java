package com.example.messenger_2025.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetChatDetailsDto {

    private long id;

    private String name;

    private List<GetUserDetailsDto> userDetailsDtoList;

}
