package com.example.spring10.repository;

import java.util.List;

import com.example.spring10.dto.BoardDto;

public interface BoardDao {
	
	public List<BoardDto> selectPage(BoardDto dto);
	public int getCount(BoardDto dto);
	
	public void insert(BoardDto dto);
	public BoardDto getByNum(int num);
	public BoardDto getByDto(BoardDto dto);
	public int delete(int num);
	public int update(BoardDto dto);
}
