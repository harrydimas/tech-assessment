package com.alami.techassessment.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "transactionLog")
public class TransactionLog {

	@Id
	private Integer id;
	
	private Integer memberId;
	
	private Double amount;
	
	private String trxType;
	
	private LocalDateTime trxDate;
	
	private String description;

	public TransactionLog(Transaction transaction) {
		this.id = transaction.getId();
		this.memberId = transaction.getMemberId().getId();
		this.amount = transaction.getAmount();
		this.trxType = transaction.getTrxType();
		this.trxDate = transaction.getTrxDate();
		this.description = transaction.getDescription();
	}
	
}
