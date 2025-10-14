package com.example.spring11.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.spring11.handler.MySocketHandler;

@Configuration //설정을 위한 어노테이션
@EnableWebSocket //웹소켓을 사용하기 위한 어노테이션
public class WebSocketConfig implements WebSocketConfigurer{
	
	//웹소켓 핸들러를 등록하는 메소드 
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// "/ws" 경로의 웹소캣 연결을 해 오면 MySocketHandler 객체로 처리를 하겠다는 의미 
		registry.addHandler(new MySocketHandler(), "/ws")
			.setAllowedOrigins("*"); // 개발중에는 CORS 허용 
	}

}







