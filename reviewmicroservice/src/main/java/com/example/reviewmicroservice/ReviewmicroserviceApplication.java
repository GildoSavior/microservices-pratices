package com.example.reviewmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ReviewmicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewmicroserviceApplication.class, args);
	}

}
