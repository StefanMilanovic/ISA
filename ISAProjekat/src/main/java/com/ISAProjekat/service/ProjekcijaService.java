package com.ISAProjekat.service;


import java.util.List;

import com.ISAProjekat.model.Projekcija;

public interface ProjekcijaService {
	List<Projekcija>findAll();
	Projekcija save(Projekcija p);
	Projekcija findProjekcijaById(Long id);
	void delete(Long id);
}
