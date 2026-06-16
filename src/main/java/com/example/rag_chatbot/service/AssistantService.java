package com.example.rag_chatbot.service;



import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface AssistantService {

    @SystemMessage("You are a helpful AI assistant. Keep answers short and clear.")
    String chat(@UserMessage String message);
}