package com.ISAProjekat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ISAProjekat.model.Bioskop;
import com.ISAProjekat.model.Pozoriste;
import com.ISAProjekat.model.User;

@SpringBootApplication
public class IsaProjekatApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaProjekatApplication.class, args);
		
		User u   = new User();
		
		u.setEmail("asd");
		u.setPassword("asd");
		System.out.println(u.getEmail());
		
		Bioskop b1 = new Bioskop(111L,"Bioskop1","Stefana Milanovica BB, Novi Sad","Novi bioskop u gradu, 200 sala, besplatni prikazi prvih 300 godina.",
				null,null, 4.7);
		Bioskop b2 = new Bioskop(111L,"Bioskop2","Nadje Loncar BB, Novi Sad","Novi bioskop u gradu, 1500 sala, besplatni prikazi prvih 35 godina.",
				null,null, 4.5);
		Pozoriste p1 = new Pozoriste(222L,"Pozoriste1","Neznanog junaka 22","Novo pozoriste otvoreno u Novom Sadu. Sirok repertoar drama, pocevsi od Sekspira do Nusica i Sterije.",
				null,null, 5.0);
		Pozoriste p2 = new Pozoriste(222L,"Pozoriste2","Ivana Lukovica BP2","Novo pozoriste otvoreno u Novom Sadu. Sirok repertoar drama, pocevsi od Sekspira do Nusica i Sterije.",
				null,null, 2.0);
	}
}
