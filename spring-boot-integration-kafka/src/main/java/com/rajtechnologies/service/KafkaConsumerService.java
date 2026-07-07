package com.rajtechnologies.service;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "test-topic", groupId = "mygroup")
    public void consumeMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
