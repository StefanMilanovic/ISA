package com.ISAProjekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ISAProjekat.model.Oglas;

public  interface  OglasRepository  extends JpaRepository<Oglas, Long>{
	Oglas findByNazivIgnoreCaseContaining(String email);
}
