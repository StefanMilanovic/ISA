
package com.ISAProjekat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ISAProjekat.model.Korisnik;
import com.ISAProjekat.repository.KorisnikRepository;
import com.ISAProjekat.service.KorisnikService;
@Service
public class KorisnikServiceImpl implements KorisnikService {

	
	@Autowired
	private KorisnikRepository korisnikRepository;

	@Override
	public Korisnik findKorisnikByEmail(String email) {
		Assert.notNull(email, "Email ne sme biti null");
		// TODO Auto-generated method stub
		return this.korisnikRepository.findByEmailIgnoreCaseContaining(email);
		
	}

	@Override
	public List<Korisnik> findAll() {
		return this.korisnikRepository.findAll();
		
	}

	@Override
	public Korisnik save(Korisnik k) {
		// TODO Auto-generated method stub
		return korisnikRepository.save(k);
	}

}

