package com.eas.tutorial.jwtspringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="*") // para permitir todos los origenes
@EnableAutoConfiguration
@EnableConfigurationProperties //fundamental
@SpringBootApplication
public class JwtSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtSpringSecurityApplication.class, args);
	}

}
