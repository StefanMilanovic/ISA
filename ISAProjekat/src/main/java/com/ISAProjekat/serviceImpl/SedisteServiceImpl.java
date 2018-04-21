package com.ISAProjekat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ISAProjekat.model.Sediste;
import com.ISAProjekat.repository.SedisteRepository;
import com.ISAProjekat.service.SedisteService;

@Service
public class SedisteServiceImpl implements SedisteService{
	@Autowired
	SedisteRepository sedisteRepository;

	@Override
	public List<Sediste> findAll() {
		return this.sedisteRepository.findAll();
	}

	@Override
	public Sediste save(Sediste s) {
		return this.sedisteRepository.save(s);
	}

	@Override
	public Sediste findSedisteById(Long id) {
		return this.sedisteRepository.findOne(id);
	}
	
}
