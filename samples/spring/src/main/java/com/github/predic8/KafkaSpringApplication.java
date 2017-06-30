package com.github.predic8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ProducerFactory;

@EnableKafka
@SpringBootApplication
public class KafkaSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaSpringApplication.class, args);
    }
}