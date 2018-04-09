package com.ISAProjekat.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.ISAProjekat.model.Korisnik;

public interface KorisnikRepository extends Repository<Korisnik, Long> {
//broj rezultata long Npr. ako se prosledi objekat: new PageRequest(0, 10)
	
	List<Korisnik> findAll();
	
	
	//select c from User c where c.email = ?1
	Korisnik findByEmail(String email);


	
}

