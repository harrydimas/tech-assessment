package com.alami.techassessment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alami.techassessment.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

}
