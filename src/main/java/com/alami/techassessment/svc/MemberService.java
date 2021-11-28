package com.alami.techassessment.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alami.techassessment.dto.MemberDto;
import com.alami.techassessment.model.Member;
import com.alami.techassessment.repo.MemberRepository;

@Service
public class MemberService {

	private final MemberRepository memberRepository;

	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public List<Member> findAll(){
		return memberRepository.findAll();
	}
	
	public Member addMember(MemberDto memberDto) {
		Member member = new Member();
		member.setFullName(memberDto.getFullName());
		member.setBirthDate(memberDto.getBirthDate());
		member.setAddress(memberDto.getAddress());
		return memberRepository.save(member);
	}
}
