package com.hackerton.pieSystem;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // 모든 경로에 대해
				.allowedOrigins("http://54.180.115.36:3000","http://localhost:3000") // 허용할 도메인
				.allowedMethods("*") // 모든 메소드 허용
				.allowedHeaders("*") // 모든 헤더 허용
				.allowCredentials(true); // 인증 정보 허용
	}
}
