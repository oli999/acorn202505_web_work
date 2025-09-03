package com.example.spring09.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring09.dto.ClientDto;
import com.example.spring09.entity.Client;
import com.example.spring09.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService{
	
	//의존객체 생성자 주입
	private final ClientRepository clientRepo;
	
	//Client 정보 저장
	@Transactional
	@Override
	public Long addClient(ClientDto dto) {
		//dto 를 entity 로 변경해서 저장하고 리턴되는 값은 방금 저장한 Client entity 객체가 리턴된다.
		Client saved=clientRepo.save(dto.toEntity());
		//entity 에 들어 있는 번호를 리턴
		return saved.getNum();
	}
	
	//Client 목록 조회
	@Transactional(readOnly = true)
	@Override
	public List<ClientDto> getClients() {
		// entity List 를 stream 으로 만들어서 map() 함수를 이용해서 dto 의 stream 으로 만든다음 
		// dto List 로 변경하기 
		List<ClientDto> list=clientRepo.findAll().stream().map(ClientDto::toDto).toList();
		return list;
	}
	//Client 한명의 정보 조회
	@Transactional(readOnly = true)
	@Override
	public ClientDto getClient(Long num) {
		//Client entity = clientRepo.findById(num).get();
		
		Client entity = clientRepo.findById(num)
				.orElseThrow(()->new IllegalArgumentException("존재하지 않음 num="+num));
		// entity 를 dto 로 변경해서 리턴한다 
		return ClientDto.toDto(entity);
	}
	
	@Transactional
	@Override
	public void updateBirthday(Long num, LocalDate birthday) {
		//번호에 해당하는 entity 를 가져와서 
		Client entity=clientRepo.findById(num).get();
		//생일 날짜를 넣어준다. 
		entity.setBirthday(birthday); // entity 를 수정하는것 만으로 자동으로 반영된다
	}
	
	@Transactional
	@Override
	public void update(ClientDto dto) {
		//번호에 해당하는 entity 를 가져와서 
		Client entity=clientRepo.findById(dto.getNum()).get();
		//이름과 생일을 수정 (entity 를 수정하는것 만으로 자동으로 반영된다)
		entity.setUserName(dto.getUserName());
		entity.setBirthday(dto.getBirthday());
	}

}











