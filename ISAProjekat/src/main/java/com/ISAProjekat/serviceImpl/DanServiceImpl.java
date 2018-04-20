package com.ISAProjekat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ISAProjekat.model.Dan;
import com.ISAProjekat.repository.DanRepository;
import com.ISAProjekat.service.DanService;

@Service
public class DanServiceImpl implements DanService{

	@Autowired
	DanRepository danRepository;

	@Override
	public List<Dan> findAll() {
		return this.danRepository.findAll();
	}

	@Override
	public Dan findDanById(Long id) {
		return this.danRepository.findById(id);
	}

	@Override
	public Dan save(Dan d) {
		return this.danRepository.save(d);
	}
	
}
