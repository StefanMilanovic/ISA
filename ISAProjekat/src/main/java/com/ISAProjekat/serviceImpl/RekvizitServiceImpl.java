package com.ISAProjekat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ISAProjekat.model.Rekvizit;
import com.ISAProjekat.repository.BlagajnaRepository;
import com.ISAProjekat.repository.RekvizitRepository;
import com.ISAProjekat.service.RekvizitService;
@Service
public class RekvizitServiceImpl implements RekvizitService {

	@Autowired
	private RekvizitRepository rekvizitRepository;
	
	@Autowired
	private BlagajnaRepository blagajnaRepository;
	
	@Override
	public Rekvizit save(Rekvizit rekvizit) {
		if(rekvizit.getBlagajna() == null){
			rekvizit.setBlagajna(blagajnaRepository.findOne((long) 1));
		}
		return rekvizitRepository.save(rekvizit);
		//return null;
	}

	@Override
	public List<Rekvizit> save(List<Rekvizit> rekviziti) {
		return rekvizitRepository.save(rekviziti);
		//return null;
	}

	@Override
	public List<Rekvizit> findAll() {
		return rekvizitRepository.findAll();
		//return null;
	}

	@Override
	public boolean delete(Long id) {
		Rekvizit rekvizit = rekvizitRepository.findOne(id);
		if(rekvizit == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant rekvizit");
		}
		rekvizitRepository.delete(rekvizit);
		return true;
		//return null;
	}

	@Override
	public Rekvizit findById(Long id) {
		return rekvizitRepository.findOne(id);
		//return null;
	}

}
