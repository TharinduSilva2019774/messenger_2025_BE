package com.example.messenger_2025.service;

import com.example.messenger_2025.payload.GetChatGPTRequestDto;
import com.example.messenger_2025.payload.GetMessageResponseDto;
import com.example.messenger_2025.payload.PostMessageDto;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;





@Service
public class ChatGPTService {

    @Autowired
    MessageService messageService;

    public GetMessageResponseDto chatGPTResponse(GetChatGPTRequestDto getChatGPTRequestDto){
        OpenAIClient client = OpenAIOkHttpClient.fromEnv();
        String promt = """
                Bellow are the chat messages attached for this chat looking at the given messages make an appropriate response for the last asked question. 
               
                *""" + getChatGPTRequestDto.getContext() + """
                
                """;

        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .addUserMessage(promt)
                .model("gpt-4.0")
                .build();
        ChatCompletion chatCompletion = client.chat().completions().create(params);
        Optional<String> optString = chatCompletion.choices().getFirst().message().content();
        if(optString.isPresent()){
            messageService.postMessage(new PostMessageDto(optString.get(),"ChatGPT", getChatGPTRequestDto.getChatId(), getChatGPTRequestDto.getClarkId()));
            messageService.postMessage(new PostMessageDto(optString.get(),"ChatGPT", getChatGPTRequestDto.getChatId(), getChatGPTRequestDto.getOtherClarkId()));
        }
        return null;
    }

}
