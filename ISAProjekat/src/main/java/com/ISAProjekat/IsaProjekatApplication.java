package com.ISAProjekat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ISAProjekat.model.Bioskop;
import com.ISAProjekat.model.Korisnik;
import com.ISAProjekat.model.Pozoriste;

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
