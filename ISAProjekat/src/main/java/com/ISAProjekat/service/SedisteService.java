package com.ISAProjekat.service;

import java.util.List;

import com.ISAProjekat.model.Sediste;

public interface SedisteService {
	List<Sediste>findAll();
	Sediste save(Sediste s);
	Sediste findSedisteById(Long id);
}
