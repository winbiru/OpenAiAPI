package com.demo.openaiapi.serviceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.demo.openaiapi.service.OpenAIService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OpenAIServiceImpl implements OpenAIService {
    private final String apiKey;
    private final String endpoint;
    private final RestTemplate restTemplate;

    public OpenAIServiceImpl(@Value("${openai.api.key}")String apiKey, @Value("${openai.api.endpoint}") String endpoint, RestTemplateBuilder restTemplateBuilder) {
        this.apiKey = apiKey;
        this.endpoint = endpoint;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String sendChatMessage(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> data = new HashMap<>();
        data.put("prompt", message);
        data.put("model", "davinci-codex");
        data.put("temperature", 0.5);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(data, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(endpoint +"completions", request, Map.class);

        List<Map<String, Object>> choisesList = (List<Map<String, Object>>) response.getBody().get("choices");
        StringBuilder choicesBuilder = new StringBuilder();
        for (Map<String, Object> choice : choisesList){
            choicesBuilder.append(choice.get("text"));
        }
        return choicesBuilder.toString();
    }
}
