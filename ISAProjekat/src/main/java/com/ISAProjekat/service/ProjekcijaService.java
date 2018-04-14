package com.ISAProjekat.service;


import java.util.List;

import com.ISAProjekat.model.Projekcija;

public interface ProjekcijaService {
	List<Projekcija>findAll();
//	Projekcija findProjekcijaById()
	Projekcija save(Projekcija p);
}
