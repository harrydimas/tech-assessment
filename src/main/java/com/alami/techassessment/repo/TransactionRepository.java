package com.alami.techassessment.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alami.techassessment.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	@Query("FROM Transaction WHERE trxDate BETWEEN ?1 AND ?2")
	List<Transaction> getByTrxDate(LocalDateTime startDate, LocalDateTime endDate);
	
}
