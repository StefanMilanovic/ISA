package com.ISAProjekat.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ISAProjekat.model.BodovnaSkala;
import com.ISAProjekat.model.Rekvizit;
import com.ISAProjekat.repository.BodovnaSkalaRepository;
import com.ISAProjekat.service.BodovnaSkalaService;
@Service
public class BodovnaSkalaServiceImpl implements BodovnaSkalaService {
	@Autowired
	private BodovnaSkalaRepository bodovnaSkalaRepository;

	@Override
	public BodovnaSkala findById(Long id) {
		return bodovnaSkalaRepository.findOne(id);
	}
	
	@Override
	public BodovnaSkala save(BodovnaSkala bodovnaSkala) {
		
		return bodovnaSkalaRepository.save(bodovnaSkala);
		//return null;
	}
}
