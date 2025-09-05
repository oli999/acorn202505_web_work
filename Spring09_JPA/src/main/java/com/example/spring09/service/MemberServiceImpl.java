package com.example.spring09.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring09.dto.MemberDto;
import com.example.spring09.entity.Member;
import com.example.spring09.repository.MemberRepository;


import lombok.RequiredArgsConstructor;

//서비스 클래스에 붙여줄 어노테이션
@Service
@RequiredArgsConstructor // lombok 이 생성자를 자동으로 만들어 주도록 한다 
public class MemberServiceImpl implements MemberService{
	
	//JPA Repository 객체를 주입 받는다.
	private final MemberRepository memberRepo;
	
	//select 전용 메소드는 @Transactional(readOnly=true) 를 붙이면 안전하다
	//의도치 않은 수정, 삭제를 방지하기 위해 
	@Transactional(readOnly = true)
	@Override
	public List<MemberDto> getAll() {
		//전체 회원목록을 얻어낸다 (Entity 의 목록)
		/*
		List<Member> list=memberRepo.findAll();
		List<MemberDto> dtoList=new ArrayList<>();
		for(Member tmp:list) {
			MemberDto dto=MemberDto.builder()
					.num(tmp.getNum())
					.name(tmp.getName())
					.addr(tmp.getAddr())
					.build();
			dtoList.add(dto);
		}
		*/
		/*
		 *  Entity 의 List 를 stream 으로 만들어서 map() 함수를 이용해서 
		 *  stream 에 저장된 Entity 를 dto 로 변경한 다음 
		 *  List 로 변경한다 
		 *  
		 *  클래스명 :: static 메소드명  은 클래스안에 만들어진 static 메소드를 참조하는 표현식이다 
		 *  
		 *  즉 .map() 함수 안에서 사용될 메소드를 미리만들어진 메소드를 참조해서 전달하는 방식이다 
		 */
		
		// id 칼럼에 대해서 default 로 오름 차순 정렬된 List 가 반환된다.
		//List<MemberDto> dtoList=memberRepo.findAll().stream().map(MemberDto::toDto).toList();
		
		// Sort.Direction.ASC or Sort.Direction.DESC  
		// "num" 은 num 이라는 칼럼에 대해서 정렬하라는 의미 
		//List<MemberDto> dtoList=memberRepo.findAll(Sort.by(Sort.Direction.DESC, "num"))
		//		.stream().map(MemberDto::toDto).toList();
		
		
		//repository 인터페이스에 정해진 형식의 메소드를 만들어서 정렬된 결과를 얻어낼수도 있다.
		//List<MemberDto> dtoList=memberRepo.findAllByOrderByNumDesc()
		//		.stream().map(MemberDto::toDto).toList();
		
		//repository 인터페이스에 작성한 JPQL 를 이용해서 정렬된 결과 얻어내기 
		//List<MemberDto> dtoList=memberRepo.findAllQuery()
		//		.stream().map(MemberDto::toDto).toList();
		
		//repository 인터페이스에 작성한 Native Query 문을 이용해서 정렬된 결과 얻어내기 
		List<MemberDto> dtoList=memberRepo.findAllNativeQuery()
				.stream().map(MemberDto::toDto).toList();
		
		return dtoList;
	}
	
	@Transactional(readOnly = true)
	@Override
	public MemberDto getMember(int num) {
		//번호를 이용해서 Member entity 객체를 얻어내서 
		//Member entity=memberRepo.findById(num).get();
		
		// num 에 해당하는 Member entity 를 얻어내는데 만일 존제 하지 않는다면 예외 발생 시키기 
		Member entity=memberRepo.findById(num)
				.orElseThrow(()-> new IllegalArgumentException("회원이 존재하지 않습니다 num="+num));
		
		//entity 를 dto 로 변경해서 리턴한다. 
		return MemberDto.toDto(entity);
	}
	
	@Transactional
	@Override
	public MemberDto addMember(MemberDto dto) {
		/*
		 *  dto 를 Entity 로 변경해서 save() 메소드에 전달하면 된다 
		 *  
		 *  - Entity 의 id 필드에 해당하는 정보가 DB 에 없으면 insert 된다
		 *  - Entity 의 id 필드에 해당하는 정보가 DB 에 이미 존재하면 update 된다. 
		 */
		
		// insert or update 된 entity 를 리턴해준다.
		Member m = memberRepo.save(dto.toEntity());
		//방금 추가한 회원의 정보를 리턴해준다.
		return MemberDto.toDto(m);
	}
	/*
	 *  Entity 를 수정해서 DB 에 반영되게 하려면 @Transactional 은 필수 
	 *  
	 *  Entity 변경 감지를 하기 위해 
	 */
	@Transactional
	@Override
	public void updateMember(MemberDto dto) {
		// memberRepo.save(Member.toEntity(dto));
		
		//위의 경우는 잘못된 데이터가 DB 에 insert 될 위험이 있기때문에 아래의 방식을 많이 사용한다 
		Member entity=memberRepo.findById(dto.getNum())
				.orElseThrow(()->new IllegalArgumentException("수정할 회원이 존재하지 않아요!"));
		//존재할 경우 entity 객체를 수정하면 DB 에 자동 반영된다.
		entity.setName(dto.getName()); //객체를 수정하는것 만으로 DB 가 수정되는것을 확인한다 
		entity.setAddr(dto.getAddr());
	}
	
	@Transactional
	@Override
	public MemberDto deleteMember(int num) {
				
		//만일 삭제할 entity 가 존재하지 않으면 
		if(!memberRepo.existsById(num)) {
			throw new IllegalArgumentException("삭제할 회원이 존재 하지 않습니다 num="+num);
		}
		Member m=memberRepo.findById(num).get();
		//번호를 이용해서 삭제 (실패시 예외가 발생하지는 않는다)
		memberRepo.deleteById(num);
		
		return MemberDto.toDto(m);
	}

}











