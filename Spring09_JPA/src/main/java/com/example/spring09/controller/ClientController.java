package com.example.spring09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring09.dto.ClientDto;
import com.example.spring09.service.ClientService;

import jakarta.validation.Valid;
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
	
	@GetMapping("/clients/new")
	public String newForm(Model model) {
		//만일 "clientDto" 라는 키값으로 저장되어 있는 값이 model 객체에 없다면
		if (!model.containsAttribute("clientDto")) {
			//빈 ClientDto 객체라도 전달을 해 주어야 한다! 전달해 주지 않으면 thymeleaf 페이지에서 에러 발생 
	        model.addAttribute("clientDto", new ClientDto());
	    }
		return "clients/new";
	}
	/*
	 *  @Valid 어노테이션을 이용해서 dto 의 필드를 자동 검증하기 위한 의존 depenency 를 pom.xml 에 추가
	 *  
	    <dependency>
		  <groupId>org.springframework.boot</groupId>
		  <artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		
		@Valid 가 dto 의 어떤 필드를 어떤 조건으로 검증할지 dto 클래스에 표시를 해야 한다 
		
		검증 결과가 BindingResult 객체에 담겨서 전달이 된다.
		
		@Valid 로 검증을 한 dto 매개변수 선언 바로 뒤에 BindingResult 매개변수를 선언해야 한다. 
	 */
	@PostMapping("/clients")
	public String create(@Valid ClientDto dto, BindingResult br, RedirectAttributes ra) {
		//폼 입력 내용중에 에러가 있는지(검증조건을 통과하지 못했는지) 여부를 알아내서
		boolean hasError=br.hasErrors();
		//만일 에러가 있다면
		if(hasError) {
			/*
			 *  검증결과를 view page 에서 활용하려면 
			 *  Model 객체 혹은 RedirectAttributes(리다일렉트 이동할경우) 객체에 정보를 담아 주어야 한다.
			 *  
			 *  model.addAttribute("key값", dto 객체);
			 *  model.addAttribute("org.springframework.validation.BindingResult.key값", BindingResult 객체);
			 *  
			 *  or
			 *  
			 *  ra.model.addFlashAttribute("key값", dto 객체); 
			 *  ra.model.addFlashAttribute("org.springframework.validation.BindingResult.key값", 
			 *  	BindingResult 객체); 
			 *  
			 *  두개를 반드시 셋트로 "key값" 을 일치시켜서 담아야 한다 
			 */
			ra.addFlashAttribute("clientDto", dto);
			ra.addFlashAttribute("org.springframework.validation.BindingResult.clientDto", br);
			
			return "redirect:/clients/new";
		}
		//새 고객 정보를 저장한다.
		Long num = clientService.addClient(dto);
		//고객 정보 자세히 보기로 리다일렉트 
		return "redirect:/clients/"+num;
	}
}














