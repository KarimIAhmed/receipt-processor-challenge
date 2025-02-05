package com.example.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ReceiptsProcessor {
	public static void main(String[] args) {
		SpringApplication.run(ReceiptsProcessor.class, args);
	}
}
