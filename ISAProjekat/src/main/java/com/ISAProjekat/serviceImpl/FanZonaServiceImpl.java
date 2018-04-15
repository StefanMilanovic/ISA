package com.ISAProjekat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ISAProjekat.model.FanZona;
import com.ISAProjekat.repository.FanZonaRepository;
import com.ISAProjekat.service.FanZonaService;
@Service
public class FanZonaServiceImpl  implements FanZonaService{

	
	
	@Autowired
	private FanZonaRepository fanZonaRepository;
	
	@Override
	public FanZona findOne(Long id) {
		return fanZonaRepository.findOne(id);
	}

	@Override
	public List<FanZona> findAll() {
		return fanZonaRepository.findAll();
	}

	@Override
	public FanZona save(FanZona fanZona) {
		return fanZonaRepository.save(fanZona);
	}

	@Override
	public List<FanZona> save(List<FanZona> fanZone) {
		return fanZonaRepository.save(fanZone);
	}

	@Override
	public FanZona delete(Long id) {
		FanZona fanZona = fanZonaRepository.findOne(id);
		if(fanZona == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant fanZona");
		}
		fanZonaRepository.delete(fanZona);
		return fanZona;
	}
}
