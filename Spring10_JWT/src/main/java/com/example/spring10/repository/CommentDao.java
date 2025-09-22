package com.example.spring10.repository;

import java.util.List;

import com.example.spring10.dto.CommentDto;

public interface CommentDao {
	public List<CommentDto> selectList(int parentNum);
	public int delete(int num);
	public int update(CommentDto dto);
	public void insert(CommentDto dto);
	public int getSequence();
	public CommentDto getByNum(int num);
}
