package com.example.spring08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@PropertySource(value="classpath:custom.properties") // custom.properties 파일 로딩 
@SpringBootApplication
public class Spring08ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring08ExampleApplication.class, args);
	}

}
