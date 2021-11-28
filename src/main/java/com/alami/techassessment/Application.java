package com.alami.techassessment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.alami.techassessment.model.Transaction;
import com.alami.techassessment.model.TransactionLog;
import com.alami.techassessment.mongo_repo.TransactionLogMongoRepository;
import com.alami.techassessment.repo.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableMongoRepositories(basePackages = {"com.alami.techassessment.mongo_repo"})
@EnableJpaRepositories(basePackages = {"com.alami.techassessment.repo"})
@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TransactionLogMongoRepository transactionLogMongoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		List<Transaction> transactions = transactionRepository.findAll();
		transactions.stream().forEach(trx -> {
			log.info("insert to mongodb = " + trx.toString());
			TransactionLog transactionLog = transactionLogMongoRepository.findById(trx.getId()).orElse(null);
			if (null == transactionLog) {
				transactionLog = new TransactionLog(trx);
				transactionLogMongoRepository.insert(transactionLog);
			}
		});
	}

}
