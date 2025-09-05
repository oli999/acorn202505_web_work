package com.example.spring09.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
/*
    1. pom.xml 에 아래의 dependency 추가
    
    <dependency>
	    <groupId>org.springdoc</groupId>
	    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
	    <version>2.6.0</version>
	</dependency> 
	
 	2. SwaggerConfing 설정 파일 추가
 	
 	3. 웹브라우저의 주소창에 위에서 아래의 경로를 요청해서 swagger ui 를 사용할수 있다.
 	
 	/swagger-ui/index.html
 	
 */
@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI  customOpenAPI() {
		Info info=new Info()
				.title("API 문서입니다")
				.version("v1")
				.description("어쩌구... 저쩌구...");
		
		return new OpenAPI().info(info);
	}
}
