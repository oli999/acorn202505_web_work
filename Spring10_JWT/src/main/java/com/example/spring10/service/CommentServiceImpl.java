package com.example.spring10.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.spring10.dto.CommentDto;
import com.example.spring10.repository.CommentDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

	private final CommentDao commentDao;
	
	@Override
	public List<CommentDto> getComments(int parentNum) {
		
		return commentDao.selectList(parentNum);
	}

	@Override
	public void createComment(CommentDto dto) {
		//댓글의 그룹번호가 넘어오지 않으면 dto.getGroupNum() 은 0 을 리턴한다. 
		
		//저장할 댓글의 pk 를 미리 얻어낸다.
		int num=commentDao.getSequence();
		//댓글의 글번호로 사용하고 
		dto.setNum(num);
		//만일 원글의 댓글이면
		if(dto.getGroupNum() == 0) {
			dto.setGroupNum(num); //원글의 댓글은 자신의 글번호가 댓글의 그룹번호이고 
		}
		
		//댓글 작성자를 얻어내서 dto 에 담는다 
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(userName);
		
		//대댓글이면 이미 dto 에 댓글의 그룹번호가 들어 있다.
		commentDao.insert(dto);
	}

	@Override
	public void updateComment(CommentDto dto) {
		//글 작성자와 로그인된 userName 이 동일한지 비교해서 동일하지 않으면 예외를 발생시킨다.
		String writer = commentDao.getByNum(dto.getNum()).getWriter();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!writer.equals(userName)) {
			throw new RuntimeException("남의 글을 수정할수 없습니다!");
		}
		commentDao.update(dto);
		
	}

	@Override
	public void deleteComment(int num) {
		//글 작성자와 로그인된 userName 이 동일한지 비교해서 동일하지 않으면 예외를 발생시킨다.
		String writer = commentDao.getByNum(num).getWriter();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!writer.equals(userName)) {
			throw new RuntimeException("남의 글을 지울수는 없습니다!");
		}
		//글 삭제하기 
		commentDao.delete(num);
	}

}
