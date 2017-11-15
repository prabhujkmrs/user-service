package com.anger.service.userservice;

import com.anger.service.userservice.service.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

	@Autowired
	DataLoader dataLoader;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
