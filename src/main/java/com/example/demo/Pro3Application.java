package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import com.example.demo.services.StorageService;
import org.springframework.boot.CommandLineRunner;
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
public class Pro3Application {

	public static void main(String[] args) {
		SpringApplication.run(Pro3Application.class, args);
	}
	@Bean
	CommandLineRunner  init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}

}
