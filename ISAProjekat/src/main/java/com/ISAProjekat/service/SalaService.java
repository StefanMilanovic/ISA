package com.ISAProjekat.service;

import java.util.List;

import com.ISAProjekat.model.Sala;

public interface SalaService {
	
	List<Sala> findAll();
	
	Sala save(Sala s);
	
}
