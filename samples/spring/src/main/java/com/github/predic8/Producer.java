package com.github.predic8;

import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static java.util.stream.IntStream.range;

@Component
public class Producer implements CommandLineRunner {
    private final KafkaTemplate<Integer, Integer> kafkaTemplate;

    public Producer(KafkaTemplate<Integer, Integer> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        range(0, 100)
            .boxed()
            .forEach(i -> kafkaTemplate.send("test", i, i));
    }
}