package com.github.helpermethod;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

import static java.util.stream.IntStream.range;
import static org.apache.kafka.clients.producer.ProducerConfig.*;

public class Producer {
    private static Properties kafkaProperties() {
        Properties properties = new Properties();

        properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return properties;
    }

    public static void main(String[] args) {
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(kafkaProperties());

        range(0, 100)
            .mapToObj(String::valueOf)
            .map(s -> new ProducerRecord<String, String>("test", s))
            .forEach(kafkaProducer::send);

        kafkaProducer.close();
    }
}
