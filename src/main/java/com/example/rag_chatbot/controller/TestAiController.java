package com.example.rag_chatbot.controller;


import com.example.rag_chatbot.service.AssistantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestAiController {

    private final AssistantService assistantService;


    @GetMapping("/api/test-ai")
    public String testAi(@RequestParam(defaultValue = "Hello, who are you?") String message) {
        return assistantService.chat(message);
    }
}