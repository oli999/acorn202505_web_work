package com.example.spring09.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.spring09.entity.Client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ClientDto {
	private Long num;
	private String userName;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDate birthday;
	
	// static toDto() 메소드
	public static ClientDto toDto(Client client) {
		return ClientDto.builder()
                .num(client.getNum())
                .userName(client.getUserName())
                .createdAt(client.getCreatedAt())
                .updatedAt(client.getUpdatedAt())
                .birthday(client.getBirthday())
                .build();
	}
	// non static toEntity() 메소드를 만들어 보세요
   public Client toEntity() {
        return Client.builder()
                .num(this.num)
                .userName(this.userName)
                .createdAt(this.createdAt)  
                .updatedAt(this.updatedAt)   
                .birthday(this.birthday)
                .build();
    }
	
}






