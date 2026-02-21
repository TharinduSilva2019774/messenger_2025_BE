package com.example.messenger_2025.service;

import com.example.messenger_2025.payload.GetChatGPTRequestDto;
import com.example.messenger_2025.payload.GetChatGPTResponseDto;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatGPTService {

    public GetChatGPTResponseDto ChatGPTResponce(GetChatGPTRequestDto getChatGPTRequestDto){
        OpenAIClient client = OpenAIOkHttpClient.fromEnv();

        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .addUserMessage("Say this is a test")
                .model("gpt-5.2")
                .build();
        ChatCompletion chatCompletion = client.chat().completions().create(params);
        Optional<String> optString = chatCompletion.choices().getFirst().message().content();
        optString.ifPresent(System.out::println);
        return new GetChatGPTResponseDto("test");
    }

}
