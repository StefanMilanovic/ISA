package com.ISAProjekat.controller;

import java.util.ArrayList;
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

import com.ISAProjekat.model.Korisnik;
import com.ISAProjekat.model.Oglas;
import com.ISAProjekat.model.Ponuda;
import com.ISAProjekat.service.KorisnikService;
import com.ISAProjekat.service.OglasService;
import com.ISAProjekat.service.PonudaService;

@RestController
@RequestMapping(value = "/ponudaController")
public class PonudaController {
	
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private KorisnikService korisnikService;
	

	@Autowired
	private PonudaService ponudaService;

	@Autowired
	private OglasService oglasService;

	
	@RequestMapping(value="/getPonude", method = RequestMethod.GET)
	public ResponseEntity<List<Ponuda>> getPonude() {
		List<Ponuda> ponude = ponudaService.findAll();

		return new ResponseEntity<>(ponude, HttpStatus.OK);
	}
	
	//uzimanje ponuda sa odredjenog oglasa
	@RequestMapping(value="/getPonudeOglasa", method = RequestMethod.GET)
	public ResponseEntity<List<Ponuda>> getPonudeOglasa() {

		List<Ponuda> ponude = ponudaService.findAll();
		
		Oglas oglasRoditelj = null;
		oglasRoditelj = (Oglas) context.getAttribute("oglasPonuda");
		System.out.println("\n2 "+ oglasRoditelj.getId());
		
		Oglas oglasBaza = oglasService.findById(oglasRoditelj.getId());
	
		List<Ponuda> ponudeOglasa  = new ArrayList<Ponuda>();
		if(!(ponude == null)){
			for(Ponuda p : ponude){
				System.out.println("\n5  Korisnik koji je dao ponudu -> "+ p.getKorisnik().getEmail());
				if(p.getOglas().getId() ==oglasBaza.getId()){
					
					ponudeOglasa.add(p);
					
				}			
			}
		}else{
			System.out.println("Nema ponuda u bazi");
		}
		
		return new ResponseEntity<>(ponudeOglasa, HttpStatus.OK);
	}

	@RequestMapping(value="dodajPonudu",method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Ponuda> dodajPonudu(@RequestBody Ponuda ponuda, HttpServletRequest request){
	//	oglas.setOdobren(false);
	//	oglas.setProveren(false);
	//	Oglas noviOglas = oglasService.save(oglas);
		System.out.println("\nDodajem ponudu");
		
		Oglas oglasRoditelj = null;
		oglasRoditelj = (Oglas) context.getAttribute("oglasPonuda");
		
		Oglas oglasBaza = oglasService.findById(oglasRoditelj.getId());
		
		Korisnik kor = (Korisnik)request.getSession().getAttribute("aktivanKorisnik");
		Korisnik k = korisnikService.findKorisnikByEmail(kor.getEmail());
		
		ponuda.setOglas(oglasBaza);
		ponuda.setKorisnik(k);
		ponuda.setStatus("NISTA");
		Ponuda r =ponudaService.save(ponuda);
		Ponuda iz_baze = ponudaService.findById(ponuda.getId());
		
		korisnikService.findKorisnikByEmail(k.getEmail()).getPonude().add(iz_baze);			
		korisnikService.save(korisnikService.findKorisnikByEmail(k.getEmail()));
		oglasService.findById(oglasRoditelj.getId()).getPonude().add(iz_baze);
		
		return new ResponseEntity<>(r, HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping(value="/findClickedPonuda", method = RequestMethod.POST)
	public ResponseEntity<Ponuda>findSelected(@RequestBody String data, HttpServletRequest request){
		
		System.out.println(data);
		data = data.replaceAll("%22", "");
		System.out.println("NOVI DATA : "+data);
		data = data.replace("id=", "");
		System.out.println("NOVI DATA : "+data);
		
		Long id = Long.parseLong(data,10);
		
		
		
		List<Ponuda> ponude = ponudaService.findAll();
		Ponuda ret=null;
		for(Ponuda b: ponude){
			if(b.getId().compareTo(id)==0){
				ret = b;
				context.setAttribute("ponudaAktivna", ret);// samo postavimo koji je poslednji kliknut
			}
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getSelectedPonuda", method = RequestMethod.GET)
	public ResponseEntity<Ponuda>getSelectedRekvizit(HttpServletRequest request){
		
		Ponuda b = null;
		b = (Ponuda) context.getAttribute("ponudaAktivna");
		
		return new ResponseEntity<>(b, HttpStatus.OK);
	}
	
}
