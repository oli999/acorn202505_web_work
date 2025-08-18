package com.example.spring04.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring04.dto.MemberDto;

// Dao 에는 보통 @Repository 어노테이션을 붙여서 bean 을 만든다 (내부적으로 추가 기능을 제공해준다)
@Repository
public class MemberDaoImpl implements MemberDao{
	// MyBatis 를 사용할때 필요한 핵심 객체 
	@Autowired 
	private SqlSession session;

	@Override
	public List<MemberDto> selectAll() {
		/*
		 *  .selectList() 를 호출하면 리턴 type 은 무조건 List<T> 이다 
		 *  List 의 generic type T 는 그때 그때 다르다
		 *  resultType 이 바로 List 의 generic type 으로 설정된다.
		 */
		List<MemberDto> list = session.selectList("member.selectAll");
		
		return list;
	}

	@Override
	public void insert(MemberDto dto) {
		session.insert("member.insert", dto);
	}

	@Override
	public int update(MemberDto dto) {
		// update 를 실행하고 update 된 row 의 갯수를 바로 리턴하기
		return session.update("member.update", dto);
	}

	@Override
	public int deleteByNum(int num) {
		// delete 를 실행하고 update 된 row 의 갯수를 바로 리턴하기
		return session.delete("member.delete", num);
	}
	/*
	 *  select 되는 row 가 1개면  session.selectOne() 메소드를 사용하고
	 *  select 되는 row 가 여러개일 가능성이 있으면 session.selectList() 메소드를 사용해서 select 한다 
	 */
	@Override
	public MemberDto getByNum(int num) {
		MemberDto dto=session.selectOne("member.getByNum", num);
		return dto;
	}

}
















