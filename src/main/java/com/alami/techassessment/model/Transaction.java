package com.alami.techassessment.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member memberId;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "trx_type", length = 1)
	private String trxType;
	
	@Column(name = "trx_date")
	private LocalDateTime trxDate;
	
	@Column(name = "description")
	private String description;

	public Transaction(TransactionLog log, Member member) {
		this.id = log.getId();
		this.memberId = member;
		this.amount = log.getAmount();
		this.trxType = log.getTrxType();
		this.trxDate = log.getTrxDate();
		this.description = log.getDescription();
	}
	
}
