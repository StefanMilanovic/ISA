package com.ISAProjekat.service;

import java.util.List;

import com.ISAProjekat.model.Korisnik;

public interface KorisnikService {

	Korisnik findKorisnikByEmail(String email);
	List<Korisnik> findAll();
	
	Korisnik save(Korisnik k);
}

