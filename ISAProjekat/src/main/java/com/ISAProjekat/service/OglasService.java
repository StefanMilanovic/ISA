package com.ISAProjekat.service;

import java.util.List;

import com.ISAProjekat.model.Oglas;
import com.ISAProjekat.model.Rekvizit;

public interface OglasService {

	Oglas findOglasByNaziv( String naziv);
	Oglas findOne(Long id);
	List<Oglas> findAll();
	List<Oglas> save(List<Oglas> oglasi);
	Oglas save(Oglas oglas);
	Oglas delete(Long id);
	Oglas findById(Long id);
	
}
