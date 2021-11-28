package com.alami.techassessment.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.alami.techassessment.model.TransactionLog;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Value(value = "${kafka.address}")
	private String kafkaAddress;

	@Bean
	public ConsumerFactory<String, TransactionLog> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "transactionLog");
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(TransactionLog.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, TransactionLog> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, TransactionLog> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
