package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RlsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RlsApplication.class, args);

		System.out.println("=======================================");
        System.out.println(" RLS Application is running successfully");
        System.out.println("=======================================");
	}

}
