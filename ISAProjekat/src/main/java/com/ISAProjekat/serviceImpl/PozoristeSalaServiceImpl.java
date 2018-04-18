package com.ISAProjekat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ISAProjekat.repository.PozoristeSaleRepository;
import com.ISAProjekat.service.PozorisnaSalaService;


@Service
public class PozoristeSalaServiceImpl implements PozorisnaSalaService{

	@Autowired
	PozoristeSaleRepository pozoristeSaleRepozitory;
	
	@Override
	public List<com.ISAProjekat.model.PozorisnaSala> findAll() {
		return this.pozoristeSaleRepozitory.findAll();
	}

	@Override
	public com.ISAProjekat.model.PozorisnaSala save(com.ISAProjekat.model.PozorisnaSala s) {
		// TODO Auto-generated method stub
		return this.pozoristeSaleRepozitory.save(s);
	}

	@Override
	public com.ISAProjekat.model.PozorisnaSala findSalaById(Long id) {
		return this.pozoristeSaleRepozitory.findOne(id);
	}


}
