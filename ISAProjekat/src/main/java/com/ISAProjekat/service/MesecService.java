package com.ISAProjekat.service;

import java.util.List;

import com.ISAProjekat.model.Mesec;

public interface MesecService {
	List<Mesec>findAll();
	Mesec findMesecById(Long id);
	Mesec save(Mesec m);
}
