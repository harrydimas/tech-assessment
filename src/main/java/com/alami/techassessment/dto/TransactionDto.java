package com.alami.techassessment.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDto {

	@NotNull
	@Min(value = 1)
	private Integer memberId;
	
	@NotNull
	@Min(value = 1)
	private Double amount;
	
	@NotBlank
	private String trxType;
	
	@NotBlank
	private String trxDate;
	
	private String description;

}
