package com.example.spring08.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring08.dto.BoardDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //의존객체 생성자로 주입받기 위한 어노테이션(lombok 의 기능)
@Repository
public class BoardDaoImpl implements BoardDao{
	
	//의존 객체
	private final SqlSession session;
	
	@Override
	public List<BoardDto> selectPage(BoardDto dto) {
		/*
		 *  1. mapper's namespace : board
		 *  2. sql's id : selectPage
		 *  3. parameterType : BoardDto
		 *  4. resultType : BoardDto ( select 된 row 하나를 어떤 type 으로 받을지를 결정해 준다 )
		 */
		return session.selectList("board.selectPage", dto);
	}

	@Override
	public List<BoardDto> selectPageByKeyword(BoardDto dto) {
		
		return session.selectList("board.selectPageByKeyword", dto);
	}

	@Override
	public int getCount() {
		// resultType : int 
		return session.selectOne("board.getCount");
	}

	@Override
	public int getCountByKeyword(String keyword) {
		/*
		 *  parameterType: string
		 *  resultType: int
		 */
		return session.selectOne("board.getCountByKeyword", keyword);
	}

	@Override
	public void insert(BoardDto dto) {
		//이 메소드를 호출하는 시점에 dto.num 은 0 이지만 
		session.insert("board.insert", dto);
		//이 메소드가 리턴된 이후에는 dto.num 에는 저장된 글번호가 들어 있다. (selectKey 의 결과값이 들어 있음)
	}

	@Override
	public BoardDto getByNum(int num) {
		
		return session.selectOne("board.getByNum", num);
	}

}



















