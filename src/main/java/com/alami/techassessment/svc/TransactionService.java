package com.alami.techassessment.svc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alami.techassessment.dto.TransactionDto;
import com.alami.techassessment.exception.TransactionException;
import com.alami.techassessment.model.Member;
import com.alami.techassessment.model.Transaction;
import com.alami.techassessment.model.TransactionLog;
import com.alami.techassessment.mongo_repo.TransactionLogMongoRepository;
import com.alami.techassessment.repo.MemberRepository;
import com.alami.techassessment.repo.TransactionRepository;

@Service
public class TransactionService {

	private final MemberRepository memberRepository;
	private final TransactionRepository transactionRepository;
	private final TransactionLogMongoRepository transactionLogMongoRepository;
	private final KafkaProducerService kafkaProducerService;

	@Autowired
	public TransactionService(MemberRepository memberRepository, TransactionRepository transactionRepository,
			TransactionLogMongoRepository transactionLogMongoRepository, KafkaProducerService kafkaProducerService) {
		this.memberRepository = memberRepository;
		this.transactionRepository = transactionRepository;
		this.transactionLogMongoRepository = transactionLogMongoRepository;
		this.kafkaProducerService = kafkaProducerService;
	}
	
	public Transaction addTransaction(TransactionDto transactionDto) throws TransactionException {
		Transaction transaction = new Transaction();
		transaction.setMemberId(memberRepository.findById(transactionDto.getMemberId()).orElse(null));
		transaction.setAmount(transactionDto.getAmount());
		transaction.setTrxType(transactionDto.getTrxType());
		transaction.setDescription(transactionDto.getDescription());
		LocalDate trxDate = LocalDate.parse(transactionDto.getTrxDate());
		if (trxDate.isAfter(LocalDate.now())) {
			throw new TransactionException("Transaction date can't more than today");
		}
		transaction.setTrxDate(trxDate.atTime(LocalTime.now()));
		
		transaction = transactionRepository.save(transaction);
		
		TransactionLog transactionLog = new TransactionLog(transaction);
		kafkaProducerService.sendMessage(transactionLog);
		
		return transaction;
	}
	
	public List<Transaction> getByMemberId(Integer memberId){
		List<TransactionLog> logs = transactionLogMongoRepository.findByMemberId(memberId);
		Member member = memberRepository.findById(memberId).orElse(null);
		List<Transaction> transactions = logs.stream().map(log -> {
			return new Transaction(log, member);
		}).collect(Collectors.toList());
		return transactions;
	}
	
	public List<Transaction> getByTrxDate(LocalDateTime startDate, LocalDateTime endDate) {
		return transactionRepository.getByTrxDate(startDate, endDate);
	}
	
}
