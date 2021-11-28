package com.alami.techassessment.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.alami.techassessment.model.TransactionLog;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaProducerService {

	@Value(value = "${kafka.topic}")
	private String kafkaTopic;

	@Autowired
	private KafkaTemplate<String, TransactionLog> kafkaTemplate;

	public void sendMessage(TransactionLog transactionLog) {
		ListenableFuture<SendResult<String, TransactionLog>> future = kafkaTemplate.send(kafkaTopic, transactionLog);
		future.addCallback(new ListenableFutureCallback<SendResult<String, TransactionLog>>() {
			@Override
			public void onSuccess(SendResult<String, TransactionLog> result) {
				log.info("Sent message=[" + transactionLog.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}
			
			@Override
			public void onFailure(Throwable ex) {
				log.info("Unable to send message=[" + transactionLog.toString() + "] due to : " + ex.getMessage());
			}
		});
	}

}