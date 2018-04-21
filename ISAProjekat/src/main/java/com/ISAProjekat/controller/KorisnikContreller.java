package com.ISAProjekat.controller;

import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ISAProjekat.model.BodovnaSkala;
import com.ISAProjekat.model.Korisnik;
import com.ISAProjekat.model.Ponuda;
import com.ISAProjekat.model.Rekvizit;
import com.ISAProjekat.service.BodovnaSkalaService;
import com.ISAProjekat.service.KorisnikService;

@RestController
@RequestMapping("/korisnikController")
public class KorisnikContreller {
	
	@Autowired
	private KorisnikService korisnikService ;

	
	@Autowired
	private BodovnaSkalaService bodovnaSkalaService;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="getKorisnici", method = RequestMethod.GET) 
	public ResponseEntity<List<Korisnik>> getKorisnici() {
		List<Korisnik> korisnici = korisnikService.findAll();
		return new ResponseEntity<>(korisnici, HttpStatus.OK);
	}
		
	
	
	@RequestMapping(value = "/prijava", method = RequestMethod.POST)
	public ResponseEntity<Korisnik> prijava(@RequestBody Korisnik requestKorisnik, HttpServletRequest request){
		
		System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Korisnik korisnik = korisnikService.findKorisnikByEmail(requestKorisnik.getEmail());
		List<Korisnik> lk = korisnikService.findAll() ;
		for(Korisnik k : lk){
			System.out.println("\n Iz BAZE podaci :"+ k.getEmail()+"->" + k.getSifra());
		}
		//System.out.println("\nbyEmail:" +korisnik.getEmail()+ "->" + korisnik.getSifra());
		if(korisnik!= null) {
			if(korisnik.getSifra().equals(requestKorisnik.getSifra())) {
				
				if(korisnik.getTipKorisnika().equals("REGISTROVAN")){
					
					BodovnaSkala bodovnaSkala=  bodovnaSkalaService.findById((long)1);
					
					System.out.println("\nBROJ POSETA "+ korisnik.getBrojPoseta() +" status:" +korisnik.getStatus()+"Granica "+ bodovnaSkala.getSrebrniBod());
					korisnik.setBrojPoseta(korisnik.getBrojPoseta()+1);

					System.out.println("\nBROJ POSETA "+ korisnik.getBrojPoseta());
					if(korisnik.getBrojPoseta() >bodovnaSkala.getSrebrniBod()){
						korisnik.setStatus("SREBRNI");
						if(korisnik.getBrojPoseta()>bodovnaSkala.getZlatniBod()){
							korisnik.setStatus("ZLATNI");
						}
					}
					korisnikService.save(korisnikService.findKorisnikByEmail(korisnik.getEmail()));
				}
				request.getSession().setAttribute("aktivanKorisnik", korisnik);//DORADI! 
				
				return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
			}
		} 
			System.out.println("\nNema zeljenog korisnika!");
		
			
		return new ResponseEntity<Korisnik>(korisnik, HttpStatus.BAD_REQUEST);
	}
	
	//registracija
	@RequestMapping(value = "/registracija", method = RequestMethod.POST)
	public ResponseEntity<Korisnik> registracija(@RequestBody Korisnik requestKorisnik){
		
		System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Korisnik preuzetKorisnik = new Korisnik( requestKorisnik.getEmail(), requestKorisnik.getSifra(), requestKorisnik.getIme(), requestKorisnik.getPrezime(), requestKorisnik.getGrad(), requestKorisnik.getTelefon(), "REGISTROVAN", false,false);
		preuzetKorisnik.setBrojPoseta(0);
		preuzetKorisnik.setStatus("BRONZANI");
		
		List<Korisnik> lk = korisnikService.findAll() ;
		
		System.out.println("\n registracija Korisnika\n"+ preuzetKorisnik.getEmail()+"\n"+preuzetKorisnik.getIme() +"\n"+preuzetKorisnik.getPrezime()+"\n"+preuzetKorisnik.getSifra()
		+"\n"+preuzetKorisnik.getGrad()+"\n"+preuzetKorisnik.getTelefon()+"\n");
		
		//ako je baza prazna samo ga dodaj bez provere 
		if(lk.isEmpty()){
			if(!preuzetKorisnik.getEmail().isEmpty() && !preuzetKorisnik.getSifra().isEmpty() && !preuzetKorisnik.getIme().isEmpty() && !preuzetKorisnik.getPrezime().isEmpty() && !preuzetKorisnik.getGrad().isEmpty()  &&  !preuzetKorisnik.getTelefon().isEmpty()) 	
				
			{
				System.out.println("\nProsao 1");
				preuzetKorisnik.setTipKorisnika("REGISTROVAN");
				preuzetKorisnik.setRekviziti(new HashSet<Rekvizit>());
				preuzetKorisnik.setPonude(new HashSet<Ponuda>());
				
				korisnikService.save(preuzetKorisnik);
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.OK);
			}else{
				System.out.println("\n NIJE Prosao 1");
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.BAD_REQUEST);
				
			}
			
		}
		
		boolean prolazi = true;
		for(Korisnik k : lk){
			if((k.getEmail().equals(preuzetKorisnik.getEmail()))){
				prolazi =false;
				System.out.println("\n ZAVRSI");
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.BAD_REQUEST);
			}else{
				
				prolazi = true;
				System.out.println("\n UPOREDI "+k.getEmail() +" i "+ preuzetKorisnik.getEmail()+"\n");
			}
		}
			if(!preuzetKorisnik.getEmail().isEmpty() && !preuzetKorisnik.getSifra().isEmpty() && !preuzetKorisnik.getIme().isEmpty() && !preuzetKorisnik.getPrezime().isEmpty() && !preuzetKorisnik.getGrad().isEmpty()  &&  !preuzetKorisnik.getTelefon().isEmpty()) 	
				
			{
				System.out.println("\nProsao2");
				preuzetKorisnik.setTipKorisnika("REGISTROVAN");
				preuzetKorisnik.setRekviziti(new HashSet<Rekvizit>());

				preuzetKorisnik.setPonude(new HashSet<Ponuda>());
				
				korisnikService.save(preuzetKorisnik);
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.OK);
			}
				System.out.println("\nEmail zauzet!");

				System.out.println("\n NIJE Prosao 2");
			
	
		
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.BAD_REQUEST);
		
	}//kraj registracije
	
	//podaci o aktivnom korisniku 
	@RequestMapping(value = "/getAktivan", method = RequestMethod.GET)
	public Korisnik getKorisnik(HttpServletRequest request){
		System.out.println("\n\t\ttrenutno aktivan korisnik: " + (Korisnik)request.getSession().getAttribute("aktivanKorisnik"));
		return (Korisnik)request.getSession().getAttribute("aktivanKorisnik");	
	}
	
	
	
	//ADMIN FAN ZONA AZURIRANJE
	@RequestMapping(value = "/azuriraj", method = RequestMethod.PUT)
	public Korisnik azuriraj(@RequestBody Korisnik requestKorisnik,HttpServletRequest request){
		
		//System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Korisnik korAzuriraj = new Korisnik();
		korAzuriraj = (Korisnik)request.getSession().getAttribute("aktivanKorisnik");	
		korAzuriraj.setEmail(requestKorisnik.getEmail());
		korAzuriraj.setSifra(requestKorisnik.getSifra());
		korAzuriraj.setIme(requestKorisnik.getIme());
		korAzuriraj.setPrezime(requestKorisnik.getPrezime());
		korAzuriraj.setGrad(requestKorisnik.getGrad());
		korAzuriraj.setTelefon(requestKorisnik.getTelefon());
	//	korAzuriraj.setTipKorisnika("ADMIN_FAN");
		
		request.getSession().setAttribute("aktivanKorisnik", korAzuriraj);
		korisnikService.save(korAzuriraj);
		
		return korAzuriraj;
	}//kraj admin fan zona azuriranje
	
	
	@RequestMapping(value = "/odjava", method = RequestMethod.GET)
	public boolean odjava(HttpServletRequest request) {
		request.getSession().invalidate();
		return true;
	}
	
	//promenaLozAdminFan
	
	@RequestMapping(value = "/promenaLozAdminFan", method = RequestMethod.PUT)
	public Korisnik promenaLozAdminFan(@RequestBody Korisnik requestKorisnik,HttpServletRequest request){
		
		//System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Korisnik korAzuriraj = new Korisnik();
		korAzuriraj = (Korisnik)request.getSession().getAttribute("aktivanKorisnik");	
		
		korAzuriraj.setSifra(requestKorisnik.getSifra());
		if(!korAzuriraj.isBioUlogovan()){
			System.out.println("\n Prvi put se ulogovao !  vrednost :"+ korAzuriraj.isBioUlogovan());
			korAzuriraj.setBioUlogovan(true);// prvo logovanje
			System.out.println("\n Prvi put se ulogovao !  vrednost :"+ korAzuriraj.isBioUlogovan());
			
		}
		
		request.getSession().setAttribute("aktivanKorisnik", korAzuriraj);
		korisnikService.save(korAzuriraj);
		
		return korAzuriraj;
	}//kraj admin fan zona promena lozinke
	
	@RequestMapping(value="/promenaLozinke", method = RequestMethod.PUT)
	public Korisnik promenaLozinke(@RequestBody Korisnik requestKorisnik, HttpServletRequest request){
		Korisnik k = (Korisnik)request.getSession().getAttribute("aktivanKorisnik");
		k.setSifra(requestKorisnik.getSifra());
		if(!k.isBioUlogovan()){
			k.setBioUlogovan(true);
		}
		request.getSession().setAttribute("aktivanKorisnik", k);
		korisnikService.save(k);
		return k;
	}
	
	
	
	
	@RequestMapping(value = "/registracijaAdminFan", method = RequestMethod.POST)
	public ResponseEntity<Korisnik> registracijaAdminFan(@RequestBody Korisnik requestKorisnik){
		
		System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Korisnik preuzetKorisnik = new Korisnik( requestKorisnik.getEmail(), requestKorisnik.getSifra(), requestKorisnik.getIme(), requestKorisnik.getPrezime(), requestKorisnik.getGrad(), requestKorisnik.getTelefon(), "REGISTROVAN", false,false);
		
		List<Korisnik> lk = korisnikService.findAll() ;
		
		System.out.println("\n registracija Korisnika\n"+ preuzetKorisnik.getEmail()+"\n"+preuzetKorisnik.getIme() +"\n"+preuzetKorisnik.getPrezime()+"\n"+preuzetKorisnik.getSifra()
		+"\n"+preuzetKorisnik.getGrad()+"\n"+preuzetKorisnik.getTelefon()+"\n");
		
		//ako je baza prazna samo ga dodaj bez provere 
		if(lk.isEmpty()){
			if(!preuzetKorisnik.getEmail().isEmpty() && !preuzetKorisnik.getSifra().isEmpty() && !preuzetKorisnik.getIme().isEmpty() && !preuzetKorisnik.getPrezime().isEmpty() && !preuzetKorisnik.getGrad().isEmpty()  &&  !preuzetKorisnik.getTelefon().isEmpty()) 	
				
			{
				System.out.println("\nProsao 1");
				preuzetKorisnik.setTipKorisnika("ADMIN_FAN");
				preuzetKorisnik.setRekviziti(new HashSet<Rekvizit>());
				preuzetKorisnik.setStatus("BRONZANI");
				korisnikService.save(preuzetKorisnik);
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.OK);
			}else{
				System.out.println("\n NIJE Prosao 1");
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.BAD_REQUEST);
				
			}
			
		}
		
		boolean prolazi = true;
		for(Korisnik k : lk){
			if((k.getEmail().equals(preuzetKorisnik.getEmail()))){
				prolazi =false;
				System.out.println("\n ZAVRSI");
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.BAD_REQUEST);
			}else{
				
				prolazi = true;
				System.out.println("\n UPOREDI "+k.getEmail() +" i "+ preuzetKorisnik.getEmail()+"\n");
			}
		}
			if(!preuzetKorisnik.getEmail().isEmpty() && !preuzetKorisnik.getSifra().isEmpty() && !preuzetKorisnik.getIme().isEmpty() && !preuzetKorisnik.getPrezime().isEmpty() && !preuzetKorisnik.getGrad().isEmpty()  &&  !preuzetKorisnik.getTelefon().isEmpty()) 	
				
			{
				System.out.println("\nProsao2");
				preuzetKorisnik.setTipKorisnika("ADMIN_FAN");
				preuzetKorisnik.setRekviziti(new HashSet<Rekvizit>());
				preuzetKorisnik.setStatus("BRONZANI");
				
				korisnikService.save(preuzetKorisnik);
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.OK);
			}
				System.out.println("\nEmail zauzet!");

				System.out.println("\n NIJE Prosao 2");
			
	
		
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.BAD_REQUEST);
		
	}//kraj registracije
//kraj registracije admin fan
	
	@RequestMapping(value = "/registracijaAdminObj", method = RequestMethod.POST)
	public ResponseEntity<Korisnik> registracijaAdminObj(@RequestBody Korisnik requestKorisnik){
		
		System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Korisnik preuzetKorisnik = new Korisnik( requestKorisnik.getEmail(), requestKorisnik.getSifra(), requestKorisnik.getIme(), requestKorisnik.getPrezime(), requestKorisnik.getGrad(), requestKorisnik.getTelefon(), "REGISTROVAN", false,false);
		
		List<Korisnik> lk = korisnikService.findAll() ;
		
		System.out.println("\n registracija Korisnika\n"+ preuzetKorisnik.getEmail()+"\n"+preuzetKorisnik.getIme() +"\n"+preuzetKorisnik.getPrezime()+"\n"+preuzetKorisnik.getSifra()
		+"\n"+preuzetKorisnik.getGrad()+"\n"+preuzetKorisnik.getTelefon()+"\n");
		
		//ako je baza prazna samo ga dodaj bez provere 
		if(lk.isEmpty()){
			if(!preuzetKorisnik.getEmail().isEmpty() && !preuzetKorisnik.getSifra().isEmpty() && !preuzetKorisnik.getIme().isEmpty() && !preuzetKorisnik.getPrezime().isEmpty() && !preuzetKorisnik.getGrad().isEmpty()  &&  !preuzetKorisnik.getTelefon().isEmpty()) 	
				
			{
				System.out.println("\nProsao 1");
				preuzetKorisnik.setTipKorisnika("ADMIN_OBJ");
				preuzetKorisnik.setStatus("BRONZANI");
				
				korisnikService.save(preuzetKorisnik);
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.OK);
			}else{
				System.out.println("\n NIJE Prosao 1");
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.BAD_REQUEST);
				
			}
			
		}
		
		boolean prolazi = true;
		for(Korisnik k : lk){
			if((k.getEmail().equals(preuzetKorisnik.getEmail()))){
				prolazi =false;
				System.out.println("\n ZAVRSI");
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.BAD_REQUEST);
			}else{
				
				prolazi = true;
				System.out.println("\n UPOREDI "+k.getEmail() +" i "+ preuzetKorisnik.getEmail()+"\n");
			}
		}
			if(!preuzetKorisnik.getEmail().isEmpty() && !preuzetKorisnik.getSifra().isEmpty() && !preuzetKorisnik.getIme().isEmpty() && !preuzetKorisnik.getPrezime().isEmpty() && !preuzetKorisnik.getGrad().isEmpty()  &&  !preuzetKorisnik.getTelefon().isEmpty()) 	
				
			{
				System.out.println("\nProsao2");
				preuzetKorisnik.setTipKorisnika("ADMIN_OBJ");
				preuzetKorisnik.setStatus("BRONZANI");
				
				korisnikService.save(preuzetKorisnik);
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.OK);
			}
				System.out.println("\nEmail zauzet!");

				System.out.println("\n NIJE Prosao 2");
			
	
		
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.BAD_REQUEST);
		
	}//kraj registracije admin objekta
	
	@RequestMapping(value = "/registracijaAdminSist", method = RequestMethod.POST)
	public ResponseEntity<Korisnik> registracijaAdminSist(@RequestBody Korisnik requestKorisnik){
		
		System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Korisnik preuzetKorisnik = new Korisnik( requestKorisnik.getEmail(), requestKorisnik.getSifra(), requestKorisnik.getIme(), requestKorisnik.getPrezime(), requestKorisnik.getGrad(), requestKorisnik.getTelefon(), "REGISTROVAN", false,false);
		
		List<Korisnik> lk = korisnikService.findAll() ;
		
		System.out.println("\n registracija Korisnika\n"+ preuzetKorisnik.getEmail()+"\n"+preuzetKorisnik.getIme() +"\n"+preuzetKorisnik.getPrezime()+"\n"+preuzetKorisnik.getSifra()
		+"\n"+preuzetKorisnik.getGrad()+"\n"+preuzetKorisnik.getTelefon()+"\n");
		
		//ako je baza prazna samo ga dodaj bez provere 
		if(lk.isEmpty()){
			if(!preuzetKorisnik.getEmail().isEmpty() && !preuzetKorisnik.getSifra().isEmpty() && !preuzetKorisnik.getIme().isEmpty() && !preuzetKorisnik.getPrezime().isEmpty() && !preuzetKorisnik.getGrad().isEmpty()  &&  !preuzetKorisnik.getTelefon().isEmpty()) 	
				
			{
				System.out.println("\nProsao 1");
				preuzetKorisnik.setTipKorisnika("ADMIN_SIST");
				preuzetKorisnik.setStatus("BRONZANI");
				
				korisnikService.save(preuzetKorisnik);
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.OK);
			}else{
				System.out.println("\n NIJE Prosao 1");
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.BAD_REQUEST);
				
			}
			
		}
		
		boolean prolazi = true;
		for(Korisnik k : lk){
			if((k.getEmail().equals(preuzetKorisnik.getEmail()))){
				prolazi =false;
				System.out.println("\n ZAVRSI");
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.BAD_REQUEST);
			}else{
				
				prolazi = true;
				System.out.println("\n UPOREDI "+k.getEmail() +" i "+ preuzetKorisnik.getEmail()+"\n");
			}
		}
			if(!preuzetKorisnik.getEmail().isEmpty() && !preuzetKorisnik.getSifra().isEmpty() && !preuzetKorisnik.getIme().isEmpty() && !preuzetKorisnik.getPrezime().isEmpty() && !preuzetKorisnik.getGrad().isEmpty()  &&  !preuzetKorisnik.getTelefon().isEmpty()) 	
				
			{
				System.out.println("\nProsao2");
				preuzetKorisnik.setTipKorisnika("ADMIN_SIST");
				preuzetKorisnik.setStatus("BRONZANI");
				
				korisnikService.save(preuzetKorisnik);
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.OK);
			}
				System.out.println("\nEmail zauzet!");

				System.out.println("\n NIJE Prosao 2");
			
	
		
				return new ResponseEntity<Korisnik>(preuzetKorisnik, HttpStatus.BAD_REQUEST);
		
	}


}
