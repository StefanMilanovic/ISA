package com.ISAProjekat.service;

import java.util.List;

import com.ISAProjekat.model.Karta;

public interface KartaService {
	List<Karta>findAll();
	Karta save(Karta s);
	Karta findKartaById(Long id);
}
