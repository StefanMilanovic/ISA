package com.ISAProjekat.service;

import java.util.List;

import com.ISAProjekat.model.Pozoriste;

public interface PozoristeService {
	List<Pozoriste> findAll();
	Pozoriste findPozoristeById(Long id);
	Pozoriste save(Pozoriste b);
}
