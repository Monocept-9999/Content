package com.example.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ContentMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentMsApplication.class, args);
	}

}
