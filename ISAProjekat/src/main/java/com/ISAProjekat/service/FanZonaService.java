package com.ISAProjekat.service;

import java.util.List;

import com.ISAProjekat.model.FanZona;
import com.ISAProjekat.model.Oglas;

public interface FanZonaService {
	FanZona findOne(Long id);
	
	List<FanZona> findAll();
	
	FanZona save(FanZona fanZona);
	
	List<FanZona> save(List<FanZona> fanZone);
	
	FanZona delete(Long id);
	
	
}
