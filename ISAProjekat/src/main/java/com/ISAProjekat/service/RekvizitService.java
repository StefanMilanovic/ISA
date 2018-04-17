package com.ISAProjekat.service;

import java.util.List;

import com.ISAProjekat.model.Rekvizit;

public interface RekvizitService {

	Rekvizit save(Rekvizit rekvizit);
	List<Rekvizit> save(List<Rekvizit> rekvizit);
	
	List<Rekvizit> findAll();
	
	Rekvizit delete(Long id);
	Rekvizit findById(Long id);
}
