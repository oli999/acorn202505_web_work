package com.example.spring10.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring10.dto.UserDto;
import com.example.spring10.repository.UserDao;

import lombok.RequiredArgsConstructor;

@Service //bean 으로 만들기 위한 어노테이션
@RequiredArgsConstructor
public class CustomUserDetailsService  implements UserDetailsService{
	
	private final UserDao dao; 

	// userName 을 전달하면 해당 user 의 자세한 정보를 리턴하는 메소드 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//실제 DB 에 있는 내용 조회
		UserDto dto=dao.getByUserName(username);
		
		//권한 목록을 List 에 담아서  (지금은 1개 이지만)
		List<GrantedAuthority> authList=new ArrayList<>();
		// Role 을 포함한 authoryty 객체를 생성해서 authList 에 추가 
		authList.add(new SimpleGrantedAuthority(dto.getRole()));
		
		//UserDetails 객체를 생성해서 
		UserDetails ud=new User(dto.getUserName(), dto.getPassword(), authList);
		//리턴해준다.
		return ud;		
		
	}

}











