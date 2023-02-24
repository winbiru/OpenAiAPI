package com.demo.openaiapi.controller;

import com.demo.openaiapi.model.ChatMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.openaiapi.service.OpenAIService;

@RestController
public class ChatbotController {
    private final OpenAIService openaiService;

    public ChatbotController(OpenAIService openaiService){
        this.openaiService = openaiService;
    }
    @PostMapping("/chat")
    public ChatMessage sendMessage(@RequestBody ChatMessage chatMessage){
        String reponse = openaiService.sendChatMessage(chatMessage.getMessage());
        return new ChatMessage("Bot", reponse);
    }

}
