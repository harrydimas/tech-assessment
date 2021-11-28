package com.alami.techassessment.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alami.techassessment.dto.TransactionDto;
import com.alami.techassessment.exception.TransactionException;
import com.alami.techassessment.model.Transaction;
import com.alami.techassessment.svc.TransactionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/trx")
public class TransactionController {

	private final TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/add")
	public ResponseEntity addTransaction(@Valid @RequestBody TransactionDto transactionDto) {
		try {
			Transaction transaction = transactionService.addTransaction(transactionDto);
			return ResponseEntity.ok(transaction);
		} catch (TransactionException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/get-by-date")
	public ResponseEntity<List<Transaction>> getByDate(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {
		
		LocalDateTime start = LocalDate.parse(startDate).atStartOfDay();
		LocalDateTime end = LocalDate.parse(endDate).atTime(23, 59, 59);
		
		return ResponseEntity.ok(transactionService.getByTrxDate(start, end));
	}
	
	@GetMapping("/get-by-member")
	public ResponseEntity<List<Transaction>> getByMember(@RequestParam("memberId") Integer memberId) {
		return ResponseEntity.ok(transactionService.getByMemberId(memberId));
	}
	
}
