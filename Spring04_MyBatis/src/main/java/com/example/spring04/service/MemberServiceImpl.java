package com.example.spring04.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring04.dto.MemberDto;
import com.example.spring04.exception.MemberException;
import com.example.spring04.exception.MemberException.Reason;
import com.example.spring04.repository.MemberDao;

//서비스 클래스에 붙여줄 어노테이션
@Service
public class MemberServiceImpl implements MemberService{
	//의존 객체
	@Autowired 
	private MemberDao dao;
	
	@Override
	public List<MemberDto> getAll() {
		
		return dao.selectAll();
	}

	@Override
	public MemberDto getMember(int num) {
		MemberDto dto=dao.getByNum(num);
		//만일 select 되는 회원 정보가 없다면?
		if(dto==null) {
			//예외 발생시키기
			throw MemberException.notFound(num);
		}
		return dto;
	}

	@Override
	public void addMember(MemberDto dto) {
		/*
		 *  insert 과정에서 SQLException 이 발생하면 자동으로 DataAccessException 이 발생한다.
		 *  dao 에 붙여놓은 @Repository 어노테이션 때문에 
		 */
		dao.insert(dto);
	}

	@Override
	public void updateMember(MemberDto dto) {
		int rowCount = dao.update(dto);
		//만일 수정되지 않았다면?
		if(rowCount == 0) {
			throw MemberException.updateFailed(dto.getNum());
		}
	}

	@Override
	public void deleteMember(int num) {
		int rowCount = dao.deleteByNum(num);
		//만일 삭제되지 않았다면?
		if(rowCount == 0) {
			throw MemberException.deleteFailed(num);
		}
	}
	
}
