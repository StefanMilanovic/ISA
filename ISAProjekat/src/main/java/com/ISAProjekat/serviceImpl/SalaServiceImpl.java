package com.ISAProjekat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ISAProjekat.model.Sala;
import com.ISAProjekat.repository.SalaRepository;
import com.ISAProjekat.service.SalaService;

@Service
public class SalaServiceImpl implements SalaService{
	
	@Autowired
	SalaRepository salaRepository;
	

	@Override
	public List<Sala> findAll() {
		return this.salaRepository.findAll();		
	}

	@Override
	public Sala save(Sala s) {
		return salaRepository.save(s);
	}

	@Override
	public Sala findSalaById(Long id) {
		return salaRepository.findOne(id);
	}
	
}
