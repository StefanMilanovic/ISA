package com.ISAProjekat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ISAProjekat.model.Bioskop;
import com.ISAProjekat.repository.BioskopRepository;
import com.ISAProjekat.service.BioskopService;

@Service
public class BioskopServiceImpl implements BioskopService{
	
	@Autowired
	BioskopRepository bioskopRepository;
	
	@Override
	public List<Bioskop> findAll() {
		return this.bioskopRepository.findAll();
	}

	@Override
	public Bioskop save(Bioskop b) {
		return bioskopRepository.save(b);
	}

	@Override
	public Bioskop findBioskopById(Long id) {
		return this.bioskopRepository.findById(id);		
	}

}
