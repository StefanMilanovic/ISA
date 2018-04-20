package com.ISAProjekat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ISAProjekat.model.Mesec;
import com.ISAProjekat.repository.MesecRepository;
import com.ISAProjekat.service.MesecService;

@Service
public class MesecServiceImpl implements MesecService{

	@Autowired
	MesecRepository mesecRepository;
	
	@Override
	public List<Mesec> findAll() {
		return this.mesecRepository.findAll();
	}

	@Override
	public Mesec findMesecById(Long id) {
		return this.mesecRepository.findById(id);
	}

	@Override
	public Mesec save(Mesec m) {
		return this.mesecRepository.save(m);
	}

}
