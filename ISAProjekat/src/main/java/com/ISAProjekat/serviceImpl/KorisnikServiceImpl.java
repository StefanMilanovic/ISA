
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
	public Korisnik getKorisnik(String email) {
		Assert.notNull(email, "Email ne sme biti null");
		// TODO Auto-generated method stub
		return this.korisnikRepository.findByEmail(email);
		
	}

	@Override
	public List<Korisnik> findAllKorisnik() {
		return this.korisnikRepository.findAll();
		
	}

}

