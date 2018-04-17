package com.ISAProjekat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ISAProjekat.model.Bioskop;
import com.ISAProjekat.model.Korisnik;

public interface BioskopService {
	List<Bioskop> findAll();
	Bioskop findBioskopById(Long id);
	Bioskop save(Bioskop b);
}
