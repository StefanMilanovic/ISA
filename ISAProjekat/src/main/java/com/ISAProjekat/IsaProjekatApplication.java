package com.ISAProjekat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ISAProjekat.model.BodovnaSkala;
import com.ISAProjekat.model.Korisnik;

@SpringBootApplication
public class IsaProjekatApplication {
		
	public static void main(String[] args) {
		SpringApplication.run(IsaProjekatApplication.class, args);
		//test
		Korisnik u   = new Korisnik();

		u.setEmail("asd");
		u.setSifra("asd");
		System.out.println(u.getEmail());				
		
	}
	
	
}
