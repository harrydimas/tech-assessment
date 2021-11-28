package com.alami.techassessment.mongo_repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.alami.techassessment.model.TransactionLog;

@Repository
public interface TransactionLogMongoRepository extends MongoRepository<TransactionLog, Integer> {

	List<TransactionLog> findByMemberId(Integer memberId);
	
}
