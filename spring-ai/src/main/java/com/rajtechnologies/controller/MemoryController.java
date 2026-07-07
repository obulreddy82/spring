package com.rajtechnologies.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demonstrates multi-turn conversation memory.
 * Call /chat repeatedly with the same conversationId to see the model
 * remember earlier turns (e.g. ask "what's my name?" after telling it your name).
 */
@RestController
public class MemoryController {

    private final ChatClient chatClient;
    private final ChatMemory chatMemory;

    public MemoryController(ChatClient chatClient, ChatMemory chatMemory) {
        this.chatClient = chatClient;
        this.chatMemory = chatMemory;
    }

    // GET /chat?conversationId=obul&message=My name is Obul
    // GET /chat?conversationId=obul&message=What is my name?
    @GetMapping("/chat")
    public String chat(@RequestParam String conversationId, @RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .advisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                // conversationId is passed as an advisor param at call time, not in the constructor
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }
}

