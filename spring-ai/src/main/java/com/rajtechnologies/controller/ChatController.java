package com.rajtechnologies.controller;

import com.rajtechnologies.dto.MovieReview;
import com.rajtechnologies.tools.WeatherTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;
    private final WeatherTools weatherTools;


    public ChatController(ChatClient chatClient, WeatherTools weatherTools) {
        this.chatClient = chatClient;
        this.weatherTools = weatherTools;
    }

    //Get /ask?question=why is the sky blue?
    @GetMapping("/ask")
    public String ask(@RequestParam String question) {
        return chatClient.prompt()
                .user(question)
                .call()
                .content();
    }

    // Get /review?movie=Inception
    // Demonstrates structured output: the models response is parsed straight into a POJO
    @GetMapping("/review")
    public MovieReview review(@RequestParam String movie) {
        return chatClient.prompt()
                .user("Write a short review of the movie" + movie)
                .call()
                .entity(MovieReview.class);
    }

    // Get /weather?city=Antwerp
    // Demonstrates tool calling: the model decides to call WeatherTools.getTemperature(...)
    @GetMapping("/weather")
    public String weather(@RequestParam String city) {

        return chatClient.prompt()
                .user("What's a wather like in " + city + "?")
                .tools(weatherTools)
                .call()
                .content();
    }
}
