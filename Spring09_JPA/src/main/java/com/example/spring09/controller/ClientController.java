package com.example.spring09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring09.service.ClientService;

import lombok.RequiredArgsConstructor;

/*
 *   - client 목록요청
 *   
 *   GET "/client/list"  <=>  GET "/clients"
 *   
 *   - client 상세보기 요청 
 *   
 *   GET "/client/detail?num=x"  <=>  GET "/clients/x"
 *   
 *   - client 추가 form 요청
 *   
 *   GET "/client/new-form"  <=>  GET "/clients/new" 
 *   
 *   - client 실제 추가 요청
 *   
 *   POST "/client/save"  <=>  POST "/clients" 
 *   
 *   - client 수정 form 요청
 *   
 *   GET "/client/edit?num=x"   <=>  GET  "/clients/x/edit"
 *   
 *   - client 수정 반영 요청
 *   
 *   POST "/client/update"  <=> POST "/clients/x" 
 */

@RequiredArgsConstructor
@Controller
public class ClientController {
	//의존객체
	private final ClientService clientService;
	
	@GetMapping("/clients")
	public String list(Model model) {
		//응답에 필요한 데이터를 Model 객체에 담는다
		model.addAttribute("clients", clientService.getClients());
		
		// view page 에서 응답 
		return "clients/list";
	}
}














