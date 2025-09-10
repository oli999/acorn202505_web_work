package com.example.spring09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring09.dto.MemberListRequest;
import com.example.spring09.dto.MemberPageResponse;
import com.example.spring09.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/v2")
@RestController
public class RestMemberController2 {
	
	private final MemberService memberService;
	
	@GetMapping("/members")
	public MemberPageResponse list(MemberListRequest request) {
		
		return memberService.getPage(request);
	}
}







