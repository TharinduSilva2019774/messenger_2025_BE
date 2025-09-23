package com.example.messenger_2025.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetAllChatDto {

    List<GetChatDto> getChatDtoList = new ArrayList<>();

}
