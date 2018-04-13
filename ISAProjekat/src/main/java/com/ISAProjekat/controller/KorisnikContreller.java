package com.ISAProjekat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		
	
	
	@RequestMapping(value = "/prijava", method = RequestMethod.POST)
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
				
				request.getSession().setAttribute("aktivanKorisnik", korisnik);//DORADI! 
				return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
			}
		} else {
			System.out.println("\nNema zeljenog korisnika!");
		}
			
		return new ResponseEntity<Korisnik>(korisnik, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/registracija", method = RequestMethod.POST)
	public ResponseEntity<Korisnik> registracija(@RequestBody Korisnik requestKorisnik){
		
		System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Korisnik preuzetKorisnik = new Korisnik( requestKorisnik.getEmail(), requestKorisnik.getSifra(), requestKorisnik.getIme(), requestKorisnik.getPrezime(), requestKorisnik.getGrad(), requestKorisnik.getTelefon(), "REGISTROVAN");
		
		List<Korisnik> lk = korisnikService.findAll() ;
		
		System.out.println("\n registracija Korisnika\n"+ preuzetKorisnik.getEmail()+"\n"+preuzetKorisnik.getIme() +"\n"+preuzetKorisnik.getPrezime()+"\n"+preuzetKorisnik.getSifra()
		+"\n"+preuzetKorisnik.getGrad()+"\n"+preuzetKorisnik.getTelefon()+"\n");
		
		
		//ako je baza prazna samo ga dodaj bez provere 
		if(lk.isEmpty()){
			if(!preuzetKorisnik.getEmail().isEmpty() && !preuzetKorisnik.getSifra().isEmpty() && !preuzetKorisnik.getIme().isEmpty() && !preuzetKorisnik.getPrezime().isEmpty() && !preuzetKorisnik.getGrad().isEmpty()  &&  !preuzetKorisnik.getTelefon().isEmpty()) 	
				
			{
				System.out.println("\nProsap2");
				preuzetKorisnik.setTipKorisnika("REGISTROVAN");
				
				
				korisnikService.save(preuzetKorisnik);
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.OK);
			}else{
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.BAD_REQUEST);
				
			}
			
		}
		for(Korisnik k : lk){
			if(!(k.getEmail().equals(preuzetKorisnik.getEmail()))){
				if(!preuzetKorisnik.getEmail().isEmpty() && !preuzetKorisnik.getSifra().isEmpty() && !preuzetKorisnik.getIme().isEmpty() && !preuzetKorisnik.getPrezime().isEmpty() && !preuzetKorisnik.getGrad().isEmpty()  &&  !preuzetKorisnik.getTelefon().isEmpty()) 	
					
					{
						System.out.println("\nProsap2");
						preuzetKorisnik.setTipKorisnika("REGISTROVAN");
						
						
						korisnikService.save(preuzetKorisnik);
						return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.OK);
					}
			}else{
				System.out.println("Email zauzet!");
				
			}
			
		}
		return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.BAD_REQUEST);
		
	}
	
}


