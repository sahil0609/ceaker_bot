package com.github.ceakerBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CeakerBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeakerBotApplication.class, args);
	}

}
