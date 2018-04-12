package com.ISAProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ISAProjekat.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

	List<Korisnik> findAll();
	
	
	//select c from User c where c.email = ?1
	Korisnik findByEmailIgnoreCaseContaining(String email);


	
}

