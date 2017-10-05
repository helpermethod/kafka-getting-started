package com.github.helpermethod;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static java.util.Collections.singletonList;
import static org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

public class Consumer {
	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	private static Properties properties() {
		Properties properties = new Properties();

		properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
		properties.setProperty(GROUP_ID_CONFIG, "workshop");

		return properties;
	}

	public static void main(String[] args) {
		log.info("Starting consumer...");

		try (KafkaConsumer<Integer, Integer> kafkaConsumer = new KafkaConsumer<>(properties())) {
			kafkaConsumer.subscribe(singletonList("workshop"));

			kafkaConsumer.poll(0);
			kafkaConsumer.seekToBeginning(kafkaConsumer.assignment());

			while (true) {
				kafkaConsumer.poll(100).forEach(r -> log.info("offset: {}, key: {}, value: {}", r.offset(), r.key(), r.value()));
			}
		}
	}
}