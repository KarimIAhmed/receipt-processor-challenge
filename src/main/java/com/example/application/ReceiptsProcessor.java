package com.example.application;

import com.example.application.model.Item;
import com.example.application.model.Receipt;
import com.example.application.service.ReceiptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class ReceiptsProcessor {
	public static void main(String[] args) {
		SpringApplication.run(ReceiptsProcessor.class, args);
	}
}
