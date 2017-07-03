package com.github.helpermethod;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

import static java.util.Collections.singletonList;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

public class Consumer {
    private static Properties properties() {
        Properties properties = new Properties();

        properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.setProperty(GROUP_ID_CONFIG, "test");

        return properties;
    }

    public static void main(String[] args) {
        KafkaConsumer<Integer, Integer> kafkaConsumer = new KafkaConsumer<>(properties());
        kafkaConsumer.subscribe(singletonList("test"));

        while (true) {
            kafkaConsumer.poll(100).forEach(r -> System.out.printf("offset: %d, key: %d, value: %d\n", r.offset(), r.key(), r.value()));
        }
    }
}