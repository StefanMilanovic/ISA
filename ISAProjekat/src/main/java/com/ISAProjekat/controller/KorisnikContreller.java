package com.ISAProjekat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ISAProjekat.model.Korisnik;
import com.ISAProjekat.service.KorisnikService;

@RestController
@RequestMapping("/korisnikController")
public class KorisnikContreller {
	
	@Autowired
	private KorisnikService korisnikService ;

	@RequestMapping(value="getKorisnici", method = RequestMethod.GET) 
	public ResponseEntity<List<Korisnik>> getKorisnici() {
		List<Korisnik> korisnici = korisnikService.findAll();
		return new ResponseEntity<>(korisnici, HttpStatus.OK);
	}
		
	
	
	@RequestMapping(value = "/prijava", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> prijava(@RequestBody Korisnik requestKorisnik, HttpServletRequest request){
		
		System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Korisnik korisnik = korisnikService.findeKorisnikByEmail(requestKorisnik.getEmail());
		List<Korisnik> lk = korisnikService.findAll() ;
		for(Korisnik k : lk){
			System.out.println("\n Iz BAZE podaci :"+ k.getEmail()+"->" + k.getSifra());
		}
		System.out.println("\nbyEmail:" +korisnik.getEmail()+ "->" + korisnik.getSifra());
		if(korisnik!= null) {
			if(korisnik.getSifra().equals(requestKorisnik.getSifra())) {
				
				request.getSession().setAttribute("aktivanKorisnik", korisnik);
				return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
			}
		} else {
			System.out.println("\nNema zeljenog korisnika!");
		}
			
		return new ResponseEntity<Korisnik>(korisnik, HttpStatus.BAD_REQUEST);
	}
	
}


