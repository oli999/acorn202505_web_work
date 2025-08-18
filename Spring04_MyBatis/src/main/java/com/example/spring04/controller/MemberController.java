package com.example.spring04.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring04.dto.MemberDto;
import com.example.spring04.repository.MemberDao;

@Controller
public class MemberController {
	//필요한 의존 객체를 주입 받는다 
	@Autowired private MemberDao dao;
	
	@PostMapping("/member/update")
	public String update(MemberDto dto) {
		//수정할 회원의 정보가 MemberDto 객체에 담겨서 전달된다.
		dao.update(dto);
		return "member/update";
	}
	
	@GetMapping("/member/edit")
	public String edit(int num, Model model) {
		//수정할 회원의 정보를 얻어와서 
		MemberDto dto=dao.getByNum(num);
		//Model 객체에 담고
		model.addAttribute("dto", dto);
		//view page 로 forward 이동해서 응답
		return "member/edit";
	}
	
	@GetMapping("/member/delete")
	public String delete(int num) {
		/*
		 *  매개변수에 int num 을 선언하면 요청 파라미터 중에서 num 이라는 파라미터 명으로 전달되는
		 *  문자열을 자동추출해서 Integer.parseInt() 를 수행해서 실제 int 값으로 바꾼다음
		 *  해당 값을 매개 변수에 전달해 준다. 
		 *  int 값으로 바꿀수 없는 문자열이 넘어오면 에러가 발생한다
		 */
		dao.deleteByNum(num);
		
		return "member/delete";
	}
	
	@PostMapping("/member/save")
	public String save(MemberDto dto) {
		/*
		 *  매개변수에 MemberDto 를 선언하면 폼 전송되는 파라미터가 자동 추출되어서 
		 *  MemberDto 객체에 담긴 채로 전달된다.
		 *  단 MemberDto 클래스의 필드명과 폼전송되는 파라미터명이 같아야 한다. 
		 *  private String name  <=>  <input type="text" name="name" >
		 *  private String addr <=>  <input type="text" name="addr" >
		 */
		dao.insert(dto);
		
		// 회원 목록보기 "/member/list" 요청을 다시 하라는 redirect 응답 하기
		// "redirect:리다일렉트 경로" 처럼  redirect: 으로 시작하는 문자열을 리턴하면된다.
		return "redirect:/member/list";
	}
	
	@GetMapping("/member/new-form")
	public String newForm() {
		//현재 여기서 수행할 로직은 없고 view page 의 위치만 리턴해준다.
		return "member/new-form";
	}
	
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








