package com.example.spring09.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring09.dto.ClientDto;
import com.example.spring09.service.ClientService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/v1")
@RequiredArgsConstructor
@RestController
public class RestClientController {
	//의존객체
	private final ClientService clientService;
	
	@GetMapping("/clients")
	public List<ClientDto> list() {
		
		return clientService.getClients();
	}

	@PostMapping("/clients") 
	public ClientDto create(@Valid @RequestBody ClientDto dto) {
		//새 고객 정보를 저장하고 
		Long num = clientService.addClient(dto);
		//방금 추가한 회원의 번호를 dto 에 담아서 
		dto.setNum(num);
		//리턴해주기
		return dto;
	}
	
	
	
	//고객정보 상세보기 요청 처리
	@GetMapping("/clients/{num}")
	public ClientDto detail(@PathVariable Long num) {
		
		return clientService.getClient(num);
	}
	
	@PutMapping("/clients/{num}")
	public ClientDto update(@PathVariable Long num, @RequestBody @Valid ClientDto dto) {
		
		//수정반영
		clientService.update(dto);
		
		return dto;
	}
}














