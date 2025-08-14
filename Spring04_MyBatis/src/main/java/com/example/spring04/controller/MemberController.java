package com.example.spring04.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring04.dto.MemberDto;
import com.example.spring04.repository.MemberDao;

@Controller
public class MemberController {
	//필요한 의존 객체를 주입 받는다 
	@Autowired private MemberDao dao;
	
	@GetMapping("/member/list")
	public String list(Model model) {
		//회원 목록 
		List<MemberDto> list=dao.selectAll();
		//응답에 필요한 객체를 Model 객체에 담는다.
		model.addAttribute("list", list);
		
		// "/WEB-INF/views/member/list.jsp" 에서 응답하기 
		return "member/list";
	}
}








