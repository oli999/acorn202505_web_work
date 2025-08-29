package com.example.spring08.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GalleryImageDto {
	private int num;
	// gallery 테이블의 PK 참조 
    private int galleryNum;
    private String saveFileName;
    private String createdAt;
}






