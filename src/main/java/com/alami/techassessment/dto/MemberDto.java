package com.alami.techassessment.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

	@NotBlank
	@Length(min = 3)
	private String fullName;
	
	@NotNull
	private LocalDate birthDate;
	
	@NotBlank
	private String address;
	
}
