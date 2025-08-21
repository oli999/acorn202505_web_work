package com.example.spring08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring08.dto.UserDto;
import com.example.spring08.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;
	
	//회원 가입 요청 처리
	@PostMapping("/user/signup")
	public String signup(UserDto dto) {
		//UserService 객체를 이용해서 사용자 정보를 추가한다 
		service.createUser(dto);
		
		return "user/signup";
	}
	
	//회원가입 폼 요청 처리
	@GetMapping("/user/signup-form")
	public String signupForm() {
		return "user/signup-form";
	}
	
	//권한 부족시 or 403 인 경우 
	@RequestMapping("/user/denied")  //GET, POST 등 모두 가능
	public String userDenied() {
		return "user/denied";
	}
	
	
	@GetMapping("/user/loginform")
	public String loginform() {
		// templates/user/loginform.html 페이지로 forward 이동해서 응답 
		return "user/loginform";
	}
	
	//로그인이 필요한 요청경로를 로그인 하지 않은 상태로 요청하면 리다일렉트 되는 요청경로 
	@GetMapping("/user/required-loginform")
	public String required_loginform() {
		return "user/required-loginform";
	}
	
	// POST 방식 /user/login 요청후 로그인 성공인경우 forward 이동될 url 
	@PostMapping("/user/login-success")
	public String loginSuccess() {
		return "user/login-success";
	}
	
	//로그인 폼을 제출(post) 한 로그인 프로세즈 중에 forward 되는 경로이기 때문에 @PostMapping 임에 주의!
	@PostMapping("/user/login-fail")
	public String loginFail() {
		//로그인 실패임을 알릴 페이지
		return "user/login-fail";
	}	
	
}



