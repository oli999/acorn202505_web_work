package com.example.spring08.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//응답에 필요한 데이터를 하나의 객체에 담을수 있도록 클래스를 설계한다 
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardListResponse {
	//글목록
	private List<BoardDto> list;
	private int startPageNum;
	private int endPageNum;
	private int totalPageCount;
	private int pageNum;
	private int totalRow;	
	private String keyword;	
}







