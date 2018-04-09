package com.ISAProjekat.service;

import java.util.List;

import com.ISAProjekat.model.Korisnik;

public interface KorisnikService {

	Korisnik getKorisnik(String email);
	List<Korisnik> findAllKorisnik();
}

