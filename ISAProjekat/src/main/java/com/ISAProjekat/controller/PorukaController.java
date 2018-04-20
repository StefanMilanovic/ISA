package com.ISAProjekat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ISAProjekat.model.Korisnik;
import com.ISAProjekat.model.Oglas;
import com.ISAProjekat.model.Ponuda;
import com.ISAProjekat.model.Poruka;
import com.ISAProjekat.service.KorisnikService;
import com.ISAProjekat.service.OglasService;
import com.ISAProjekat.service.PorukaService;

@RestController
@RequestMapping(value = "/porukaController")
public class PorukaController {

	@Autowired
	ServletContext context;
	
	@Autowired
	private KorisnikService korisnikService;
	


	@Autowired
	private OglasService oglasService;


	@Autowired
	private PorukaService porukaService;
	
	@RequestMapping(value="/getPoruke", method = RequestMethod.GET)
	public ResponseEntity<List<Poruka>> getPoruke() {
		List<Poruka> poruka = porukaService.findAll();

		return new ResponseEntity<>(poruka, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getPorukeKorisnik", method = RequestMethod.GET)
	public ResponseEntity<List<Poruka>> getPorukeKorisnik( HttpServletRequest request) {

		Korisnik kor = (Korisnik)request.getSession().getAttribute("aktivanKorisnik");
		Korisnik k = korisnikService.findKorisnikByEmail(kor.getEmail());
		
		
		List<Poruka> poruke = porukaService.findAll();
		
		
		List<Poruka> porukeKorisnika  = new ArrayList<Poruka>();
		if(!(poruke == null)){
			for(Poruka p : poruke){
				
				
		
				if(k.getEmail().equals(p.getKorisnik().getEmail())){
						porukaService.save(porukaService.findById(p.getId()));
						porukeKorisnika.add(p);
						
					
					
				}			
			}
		}else{
			System.out.println("Nema poruke u bazi");
		}
		
		return new ResponseEntity<>(porukeKorisnika, HttpStatus.OK);
	}

}
