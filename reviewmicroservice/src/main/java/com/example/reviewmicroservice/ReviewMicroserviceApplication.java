package com.example.reviewmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ReviewMicroserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReviewMicroserviceApplication.class, args);
	}
}
