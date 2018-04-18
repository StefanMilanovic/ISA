package com.ISAProjekat.service;

import java.util.List;

import com.ISAProjekat.model.PozorisnaSala;

public interface PozorisnaSalaService {
	List<PozorisnaSala> findAll();
	
	PozorisnaSala save(PozorisnaSala s);
	
	PozorisnaSala findSalaById(Long id);
}
