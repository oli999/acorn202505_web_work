package com.example.spring10.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring10.dto.BoardDto;
import com.example.spring10.dto.BoardListResponse;
import com.example.spring10.dto.CommentDto;
import com.example.spring10.service.BoardService;
import com.example.spring10.service.CommentService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
public class BoardController {
	
	//의존 객체 생성자 주입 받기 
	private final BoardService boardService;
	private final CommentService commentService;
	
	//댓글 목록보기 요청 처리
	@GetMapping("/board/{num}/comments")
	public List<CommentDto> commentList(@PathVariable int num){
		
		return commentService.getComments(num);
	}
	
	//게시글 상세보기 요청처리
	@GetMapping("/board/{num}")
	public BoardDto detail(@PathVariable int num, BoardDto dto) {
		//dto 의 search 와 keyword 값이 null 일수도 있고 아닐수도 있다(검색어가 있다면 null 이 아니다)
		//dto 에 글번호도 담는다.
		dto.setNum(num);
		
		//BoardDto 를 전달해서 해당글의 자세한 정보를 받아온다.
		return boardService.getDetail(dto);
	}
	
	//게시글 저장 요청 처리
	@PostMapping("/board")
	public BoardDto save(@RequestBody BoardDto dto) {
		//글작성자
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(userName);
		//서비스를 이용해서 저장
		boardService.createContent(dto);
		
		return dto;
	}
	
	//게시글 목록 요청처리
	@GetMapping("/board")
	public BoardListResponse list(@RequestParam(defaultValue = "1") int pageNum, BoardDto dto) {
		
		return boardService.getBoardList(pageNum, dto);
	}
	
}










