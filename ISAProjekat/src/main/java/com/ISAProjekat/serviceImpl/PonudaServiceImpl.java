package com.ISAProjekat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ISAProjekat.model.Ponuda;
import com.ISAProjekat.repository.OglasRepository;
import com.ISAProjekat.repository.PonudaRepository;
import com.ISAProjekat.service.PonudaService;


@Transactional
@Service
public class PonudaServiceImpl implements PonudaService {

	
	@Autowired
	private OglasRepository oglasRepository;
	
	@Autowired
	private PonudaRepository ponudaRepository;
	

	
	@Override
	public Ponuda findOne(Long id) {
		return ponudaRepository.findOne(id);
	}

	@Override
	public List<Ponuda> findAll() {
		return ponudaRepository.findAll();
	}

	@Override
	public Ponuda save(Ponuda ponuda) {
		//dodeljivanje ponude fan zoni i korisniku bice u kontroleru
		return ponudaRepository.save(ponuda);
	}


	@Override
	public List<Ponuda> save(List<Ponuda> ponude) {
		return ponudaRepository.save(ponude);
	}

	@Override
	public Ponuda delete(Long id) {
		Ponuda ponuda = ponudaRepository.findOne(id);
		if(ponuda == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant ponuda");
		}
		ponudaRepository.delete(ponuda);
		return ponuda;
	}
	
	
	@Override
	public Ponuda findById(Long id) {
		return ponudaRepository.findOne(id);
		//return null;
	}
}
