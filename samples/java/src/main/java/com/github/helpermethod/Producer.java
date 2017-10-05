package com.github.helpermethod;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Random;

import static java.util.UUID.randomUUID;
import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

public class Producer {
	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	private static Properties kafkaProperties() {
		Properties properties = new Properties();

		properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());

		return properties;
	}

	public static void main(String[] args) {
		log.info("Starting producer...");

		try (KafkaProducer<String, Integer> kafkaProducer = new KafkaProducer<>(kafkaProperties())) {
			new Random()
				.ints(100, 0, 1000000)
				.boxed()
				.map(i -> new ProducerRecord<>("workshop", randomUUID().toString(), i))
				.forEach(r -> {
					log.info("key: {}, value: {}", r.key(), r.value());

					kafkaProducer.send(r);
				});
		}
	}
}