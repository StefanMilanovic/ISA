package com.ISAProjekat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ISAProjekat.model.Karta;
import com.ISAProjekat.repository.KartaRepository;
import com.ISAProjekat.service.KartaService;

@Service
public class KartaServiceImpl implements KartaService{
	@Autowired
	KartaRepository kartaRepository;

	@Override
	public List<Karta> findAll() {
		return this.kartaRepository.findAll();
	}

	@Override
	public Karta save(Karta s) {
		return this.kartaRepository.save(s);
	}

	@Override
	public Karta findKartaById(Long id) {
		return this.kartaRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		kartaRepository.delete(id);
	}
}
