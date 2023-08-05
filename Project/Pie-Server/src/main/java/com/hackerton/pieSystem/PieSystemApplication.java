package com.hackerton.pieSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.hackerton.pieSystem.*")
@ComponentScan(basePackages = { "com.hackerton.pieSystem.*" })
@EntityScan("com.hackerton.pieSystem.*")
public class PieSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PieSystemApplication.class, args);
	}

}
