package com.ISAProjekat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import model.User;

@SpringBootApplication
public class IsaProjekatApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaProjekatApplication.class, args);
		
		User u   = new User();
		
		u.setEmail("asd");
		u.setPassword("asd");
		System.out.println(u.getEmail());
	}
}
