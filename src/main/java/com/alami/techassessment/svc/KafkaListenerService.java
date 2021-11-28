package com.alami.techassessment.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.alami.techassessment.model.TransactionLog;
import com.alami.techassessment.mongo_repo.TransactionLogMongoRepository;

@Component
public class KafkaListenerService {
	
	@Autowired
	private TransactionLogMongoRepository transactionLogMongoRepository;
	
	@KafkaListener(topics = "${kafka.topic}", containerFactory = "kafkaListenerContainerFactory")
    public void listenTransactionLogTopic(TransactionLog transactionLog) {
		transactionLogMongoRepository.insert(transactionLog);
    }
	
}