package com.demo.openaiapi.controller;

import com.demo.openaiapi.model.ChatMessage;
import com.demo.openaiapi.service.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatbotController {
    private final OpenAIService openaiService;

    public ChatbotController(OpenAIService openaiService){
        this.openaiService = openaiService;
    }

    @PostMapping("/chat")
    public ChatMessage sendMessage(@RequestBody ChatMessage chatMessage){
        String response = openaiService.sendChatMessage(chatMessage.getMessage());
        return new ChatMessage("Bot", response);
    }
}
