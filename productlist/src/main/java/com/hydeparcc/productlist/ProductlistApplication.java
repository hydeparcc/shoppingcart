package com.hydeparcc.productlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductlistApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProductlistApplication.class, args);
	}

}
