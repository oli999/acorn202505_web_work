package com.example.spring09.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

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
	 *  
	 *  input type="date" 의 value 에   th:value="${birthday}"  를 출력할때 형식을 맞춰 주어야한다.
	 *  사실 ClientDto 의 birthday 라는 필드는 LocalDate type 이기 때문에 
	 *  출력할때 어떤 형식으로 출력할지를 설정해야 웹브라우저가 해당 날짜를 UI 에 제대로 표시할수 있다.
	 *  그래서 필요한 어노테이션이 @DataTimeFormat 이다  
	 */
	@PastOrPresent(message = "생일은 미래일수 없습니다")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
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






