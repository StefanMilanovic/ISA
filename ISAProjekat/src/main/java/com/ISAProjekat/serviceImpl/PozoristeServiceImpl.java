package com.ISAProjekat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ISAProjekat.model.Pozoriste;
import com.ISAProjekat.repository.PozoristeRepository;
import com.ISAProjekat.service.PozoristeService;

@Service
public class PozoristeServiceImpl implements PozoristeService{

	@Autowired
	PozoristeRepository pozoristeRepozitory;
	
	
	@Override
	public List<Pozoriste> findAll() {
		return this.pozoristeRepozitory.findAll();
	}

	@Override
	public Pozoriste findPozoristeById(Long id) {		
		return this.pozoristeRepozitory.findById(id);
	}

	@Override
	public Pozoriste save(Pozoriste b) {
		return this.pozoristeRepozitory.save(b);
	}

}
