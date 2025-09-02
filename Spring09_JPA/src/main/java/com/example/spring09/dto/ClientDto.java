package com.example.spring09.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.spring09.entity.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
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
	
	@NotBlank(message="이름은 필수 입니다")
	@Size(max=20, message="이름은 최대 20자까지 가능 합니다")
	private String userName;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	/*
	 *  @Past
	 *  @PastOrPresent
	 *  @Future 
	 *  @FutrueOrPresent
	 *  중에 하나로 검증할수 있다.
	 */
	@PastOrPresent(message = "생일은 미래일수 없습니다")
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






