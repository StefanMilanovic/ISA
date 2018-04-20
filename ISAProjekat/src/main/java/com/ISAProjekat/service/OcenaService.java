package com.ISAProjekat.service;

import java.util.List;

import com.ISAProjekat.model.Ocena;

public interface OcenaService {
	Ocena findOcenaById(Long id);
	List<Ocena>findAll();
	void save(Ocena k);
}
