package com.ISAProjekat.service;

import java.util.List;

import com.ISAProjekat.model.Poruka;

public interface PorukaService {

	
	Poruka findOne(Long id);
	List<Poruka> findAll();
	List<Poruka> save(List<Poruka> poruka);
	Poruka save(Poruka poruka);
	Poruka delete(Long id);
	Poruka findById(Long id);
}
