package com.github.predic8;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    @KafkaListener(topics = "test")
    public void listen(ConsumerRecord<Integer, Integer> r) {
        System.out.printf("offset: %d, key: %d, value: %d\n", r.offset(), r.key(), r.value());
    }
}