package com.example.spring08.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring08.dto.MemberDto;
import com.example.spring08.exception.MemberException;
import com.example.spring08.repository.MemberMapper;

import lombok.RequiredArgsConstructor;

//서비스 클래스에 붙여줄 어노테이션
@Service
@RequiredArgsConstructor // lombok 이 생성자를 자동으로 만들어 주도록 한다 
public class MemberServiceImpl implements MemberService{
	
	// MemberMapper type 을 주입 받아서 사용한다
	private final MemberMapper mapper;
	
	@Override
	public List<MemberDto> getAll() {
		
		return mapper.selectAll();
	}

	@Override
	public MemberDto getMember(int num) {
		MemberDto dto=mapper.getByNum(num);
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
		mapper.insert(dto);
	}

	@Override
	public void updateMember(MemberDto dto) {
		int rowCount = mapper.update(dto);
		//만일 수정되지 않았다면?
		if(rowCount == 0) {
			throw MemberException.updateFailed(dto.getNum());
		}
	}

	@Override
	public void deleteMember(int num) {
		int rowCount = mapper.deleteByNum(num);
		//만일 삭제되지 않았다면?
		if(rowCount == 0) {
			throw MemberException.deleteFailed(num);
		}
	}
	
}
