package com.ISAProjekat.service;

import java.util.List;

import com.ISAProjekat.model.Oglas;
import com.ISAProjekat.model.Ponuda;

public interface PonudaService {

	

	
	Ponuda findOne(Long id);
	List<Ponuda> findAll();
	List<Ponuda> save(List<Ponuda> ponude);
	Ponuda save(Ponuda ponuda);
	Ponuda delete(Long id);
	Ponuda findById(Long id);
}
