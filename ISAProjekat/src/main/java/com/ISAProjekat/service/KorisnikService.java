package com.ISAProjekat.service;

import java.util.List;

import com.ISAProjekat.model.Korisnik;

public interface KorisnikService {

	Korisnik findeKorisnikByEmail(String email);
	List<Korisnik> findAll();
	
	Korisnik save(Korisnik k);
}

