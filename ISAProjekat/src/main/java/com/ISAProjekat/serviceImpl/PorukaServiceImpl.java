package com.ISAProjekat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ISAProjekat.model.Ponuda;
import com.ISAProjekat.model.Poruka;
import com.ISAProjekat.repository.PorukaRepository;
import com.ISAProjekat.service.PorukaService;

@Transactional
@Service
public class PorukaServiceImpl implements PorukaService {

	
	@Autowired
	private PorukaRepository porukaRepository;
	

	@Override
	public Poruka findOne(Long id) {
		return porukaRepository.findOne(id);
	}

	@Override
	public List<Poruka> findAll() {
		//dodeljivanje ponude fan zoni i korisniku bice u kontroleru
		return porukaRepository.findAll();
	}

	@Override
	public List<Poruka> save(List<Poruka> poruka) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Poruka save(Poruka poruka) {
		//dodeljivanje poruke  korisniku bice u kontroleru
		return porukaRepository.save(poruka);
	}

	@Override
	public Poruka delete(Long id) {
		Poruka poruka = porukaRepository.findOne(id);
		if(poruka == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant poruka");
		}
		porukaRepository.delete(poruka);
		return poruka;
	}

	@Override
	public Poruka findById(Long id) {
		return porukaRepository.findOne(id);
		//return null;
	}

}
