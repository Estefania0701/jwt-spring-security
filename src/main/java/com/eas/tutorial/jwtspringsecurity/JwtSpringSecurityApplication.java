package com.eas.tutorial.jwtspringsecurity;

import com.eas.tutorial.jwtspringsecurity.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@Import(AppConfig.class)
@EnableConfigurationProperties // importante que esté aquí y no en AppConfig
@SpringBootApplication
public class JwtSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtSpringSecurityApplication.class, args);
	}
}
