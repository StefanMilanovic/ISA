package com.ISAProjekat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ISAProjekat.model.Ocena;
import com.ISAProjekat.repository.OcenaRepository;
import com.ISAProjekat.service.OcenaService;

@Service
public class OcenaServiceImpl implements OcenaService{

	@Autowired
	OcenaRepository ocenaRepository;
	
	@Override
	public Ocena findOcenaById(Long id) {
		return this.ocenaRepository.findById(id);
	}

	@Override
	public List<Ocena> findAll() {
		return this.ocenaRepository.findAll();
	}

	@Override
	public void save(Ocena k) {
		ocenaRepository.save(k);
	}

	@Override
	public void delete(Long id) {
		ocenaRepository.delete(id);
	}

}
