package com.fca.dev.springboot.app.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@RibbonClient(name="service.products")
@EnableFeignClients
@SpringBootApplication
public class SpringBootItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootItemServiceApplication.class, args);
	}

}
