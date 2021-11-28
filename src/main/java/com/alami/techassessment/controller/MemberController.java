package com.alami.techassessment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alami.techassessment.dto.MemberDto;
import com.alami.techassessment.model.Member;
import com.alami.techassessment.svc.MemberService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/member")
public class MemberController {

	private final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<List<Member>> getAllMember(){
		List<Member> members = memberService.findAll();
		return ResponseEntity.ok(members);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Member> addMember(@Valid @RequestBody MemberDto memberDto){
		Member member = memberService.addMember(memberDto);
		return ResponseEntity.ok(member);
	}
}
