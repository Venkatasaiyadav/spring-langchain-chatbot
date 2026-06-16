//package com.example.rag_chatbot.config;
//
//
//
//import com.example.rag_chatbot.service.AssistantService;
//import dev.langchain4j.model.chat.ChatLanguageModel;
//import dev.langchain4j.service.AiServices;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AiConfig {
//
//    @Bean
//    public AssistantService assistantService(ChatLanguageModel chatLanguageModel) {
//        return AiServices.create(AssistantService.class, chatLanguageModel);
//    }
//}