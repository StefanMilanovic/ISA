package com.ISAProjekat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ISAProjekat.model.Projekcija;
import com.ISAProjekat.repository.ProjekcijaRepository;
import com.ISAProjekat.service.ProjekcijaService;

@Service
public class ProjekcijaServiceImpl implements ProjekcijaService{
	
	@Autowired
	private ProjekcijaRepository projekcijaRepository;

	@Override
	public List<Projekcija> findAll() {
		return this.projekcijaRepository.findAll();
	}

	@Override
	public Projekcija save(Projekcija p) {
		projekcijaRepository.save(p);
		return null;
	}
	
	
}
