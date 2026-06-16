package com.example.rag_chatbot.controller;


import com.example.rag_chatbot.dto.ChatRequest;
import com.example.rag_chatbot.dto.ChatResponse;
import com.example.rag_chatbot.service.AssistantService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final AssistantService assistantService;

    public ChatController(AssistantService assistantService) {
        this.assistantService = assistantService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String answer = assistantService.chat(request.message());
        return new ChatResponse(answer);
    }
}