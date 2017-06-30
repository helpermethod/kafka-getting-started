package com.github.predic8;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    @KafkaListener(topics = "test")
    public void listen(String message) {
        System.out.println(message);
    }
}