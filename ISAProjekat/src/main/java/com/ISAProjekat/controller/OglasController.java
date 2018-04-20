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
import com.ISAProjekat.model.DTO.OglasAdap;
import com.ISAProjekat.model.DTO.OglasDTOAdap;
import com.ISAProjekat.service.FanZonaService;
import com.ISAProjekat.service.KorisnikService;
import com.ISAProjekat.service.OglasService;

@RestController
@RequestMapping(value = "/oglasController")
public class OglasController {

	
	
	@Autowired
	private OglasService oglasService;
	
	@Autowired
	private OglasAdap oglasAdap;
	
	@Autowired
	private OglasDTOAdap oglasDTOAdap;

	@Autowired
	ServletContext context;
	
	@Autowired
	private KorisnikService korisnikService;

	@Autowired
	private FanZonaService fanZonaService;

	@RequestMapping(value="/getOglasi", method = RequestMethod.GET)
	public ResponseEntity<List<Oglas>> getOglasi() {
		List<Oglas> oglasi = oglasService.findAll();
		
		System.out.println("\n Oglasi" +oglasi.get(0).getNaziv() +oglasi.get(0).getCena()+oglasi.get(0).getOpis()
				+oglasi.get(0).getSlika()+oglasi.get(0).getDatum());
		return new ResponseEntity<>(oglasi, HttpStatus.OK);
	}
	@RequestMapping(value="/getOglasiZaProveru", method = RequestMethod.GET)
	public ResponseEntity<List<Oglas>> getOglasiZaProveru() {
		List<Oglas> oglasi = oglasService.findAll();
		List<Oglas> oglasiZaProveru  = new ArrayList<Oglas>();
		for(Oglas o : oglasi ){
			if(!(o.isProveren())){
				
				oglasiZaProveru.add(o);				
			
			}
		}
		return new ResponseEntity<>(oglasiZaProveru, HttpStatus.OK);
	}
	/*
	@RequestMapping(value="getOglasi", method = RequestMethod.GET)
	public ResponseEntity<List<OglasDTO>> getOglasi() {
		System.out.println("\nUzimam oglas");
		List<Oglas> oglasi = oglasService.findAll();
		return new ResponseEntity<>(oglasAdap.convert(oglasi), HttpStatus.OK);
	}*/
	
	/*
	@RequestMapping(value="getOdobreniOglasi", method = RequestMethod.GET)
	public ResponseEntity<List<OglasDTO>> getOdobreniOglasi() {
		System.out.println("\nUzimam  odobreni oglas");
		List<Oglas> oglasi = oglasService.findAll();
		List<Oglas> odobreniOglasi  = new ArrayList<Oglas>();
		for(Oglas o : oglasi ){
			if(o.isOdobren()){
				odobreniOglasi.add(o);				
				
			}
			
		}
		return new ResponseEntity<>(oglasAdap.convert(odobreniOglasi), HttpStatus.OK);
	}
	*/
	@RequestMapping(value="getOdobreniOglasi", method = RequestMethod.GET)
	public ResponseEntity<List<Oglas>> getOdobreniOglasi() {
		System.out.println("\nUzimam  odobreni oglas");
		List<Oglas> oglasi = oglasService.findAll();
		List<Oglas> odobreniOglasi  = new ArrayList<Oglas>();
		for(Oglas o : oglasi ){
			if(o.isProveren()){
				if(o.isOdobren()){
					odobreniOglasi.add(o);				
				
				}
			}
		}
		return new ResponseEntity<>(odobreniOglasi, HttpStatus.OK);
	}
	/*stara verzija
	@RequestMapping(value="dodajOglas",method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<OglasDTO> addOglas(@RequestBody OglasDTO oglasDTO){
		Oglas noviOglas = oglasService.save(oglasDTOAdap.convert(oglasDTO));
		System.out.println("\nDodajem oglas");
		
		return new ResponseEntity<>(oglasAdap.convert(noviOglas), HttpStatus.OK);
	}*/

	@RequestMapping(value="dodajOglas",method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Oglas> addOglas(@RequestBody Oglas oglas, HttpServletRequest request){
	//	oglas.setOdobren(false);
	//	oglas.setProveren(false);
	//	Oglas noviOglas = oglasService.save(oglas);
		System.out.println("\nDodajem oglas");
		
		Korisnik kor = (Korisnik)request.getSession().getAttribute("aktivanKorisnik");
		Korisnik k = korisnikService.findKorisnikByEmail(kor.getEmail());
		
		oglas.setKorisnik(k);
		Oglas r =oglasService.save(oglas );
		Oglas iz_baze = oglasService.findById(oglas.getId());
			

		korisnikService.findKorisnikByEmail(k.getEmail()).getOglasi().add(iz_baze);
			
		korisnikService.save(korisnikService.findKorisnikByEmail(k.getEmail()));

		
		return new ResponseEntity<>(r, HttpStatus.OK);
	}
	
	
	//Za klik na odobri i ponisti
	@RequestMapping(value="/findClickedOglas", method = RequestMethod.POST)
	public ResponseEntity<Oglas>findSelected(@RequestBody String data, HttpServletRequest request){
		
		System.out.println(data);
		data = data.replaceAll("%22", "");
		System.out.println("NOVI DATA : "+data);
		data = data.replace("id=", "");
		System.out.println("NOVI DATA : "+data);
		
		Long id = Long.parseLong(data,10);
		
		
		
		List<Oglas> oglasi = oglasService.findAll();
		Oglas ret=null;
		for(Oglas b: oglasi){
			if(b.getId().compareTo(id)==0){
				ret = b;
				context.setAttribute("oglasProvera", ret);// samo postavimo koji je poslednji kliknut
			}
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getSelectedOglas", method = RequestMethod.GET)
	public ResponseEntity<Oglas>getSelectedRekvizit(HttpServletRequest request){
		
		Oglas b = null;
		b = (Oglas) context.getAttribute("oglasProvera");
		System.out.println("\nSALJE NA PROFIL: "+b.getNaziv());
		
		return new ResponseEntity<>(b, HttpStatus.OK);
	}
	
	//odobren ili ne
	@RequestMapping(value = "/odobri", method = RequestMethod.PUT)
	public Oglas odobri(@RequestBody Oglas requestOglas){
		
		Oglas o = (Oglas) context.getAttribute("oglasProvera");
		//System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Oglas iz_baze = oglasService.findById(requestOglas.getId());
		
		System.out.println("\nNAZIV MENJANOG "+requestOglas.getNaziv());
		System.out.println("\nID MENJANOG "+requestOglas.getId());
		System.out.println("\n OPIS MENJANOG "+requestOglas.getOpis());
		
		System.out.println("\nMENJAM rekvizit: "+iz_baze.getNaziv());
		oglasService.findById(requestOglas.getId()).setProveren(true);
		oglasService.findById(requestOglas.getId()).setOdobren(true);
		
		
		//System.out.println("\n naziv promenjenog "+oglasService.findById(requestOglas.getId()).getNaziv());
		context.setAttribute("oglasProvera", oglasService.findById(requestOglas.getId()));
		oglasService.save(oglasService.findById(requestOglas.getId()));
		return oglasService.findById(requestOglas.getId());
	
	}
	@RequestMapping(value = "/odbi", method = RequestMethod.PUT)
	public Oglas odbi(@RequestBody Oglas requestOglas){
		
		Oglas o = (Oglas) context.getAttribute("oglasProvera");
		//System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Oglas iz_baze = oglasService.findById(requestOglas.getId());
		
		System.out.println("\nNAZIV MENJANOG "+requestOglas.getNaziv());
		System.out.println("\nID MENJANOG "+requestOglas.getId());
		System.out.println("\n OPIS MENJANOG "+requestOglas.getOpis());
		
		System.out.println("\nMENJAM rekvizit: "+iz_baze.getNaziv());
		oglasService.findById(requestOglas.getId()).setProveren(true);
		oglasService.findById(requestOglas.getId()).setOdobren(false);
		
		
		//System.out.println("\n naziv promenjenog "+oglasService.findById(requestOglas.getId()).getNaziv());
		context.setAttribute("oglasProvera", oglasService.findById(requestOglas.getId()));
		oglasService.save(oglasService.findById(requestOglas.getId()));
		return oglasService.findById(requestOglas.getId());
	
	}
	
	//Za ponude da imamo oglas sa kog su dosli
	@RequestMapping(value="/findClickedOglasPonuda", method = RequestMethod.POST)
	public ResponseEntity<Oglas>findClickedOglasPonuda(@RequestBody String data, HttpServletRequest request){
		
		System.out.println(data);
		data = data.replaceAll("%22", "");
		System.out.println("NOVI DATA : "+data);
		data = data.replace("id=", "");
		System.out.println("NOVI DATA : "+data);
		
		Long id = Long.parseLong(data,10);
		
		
		
		List<Oglas> oglasi = oglasService.findAll();
		Oglas ret=null;
		for(Oglas b: oglasi){
			if(b.getId().compareTo(id)==0){
				ret = b;
				context.setAttribute("oglasPonuda", ret);// samo postavimo koji je poslednji kliknut
			}
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getSelectedOglasPonuda", method = RequestMethod.GET)
	public ResponseEntity<Oglas>getSelectedOglasPonuda(HttpServletRequest request){
		
		Oglas b = null;
		b = (Oglas) context.getAttribute("oglasPonuda");
		System.out.println("\nSALJE NA PROFIL: "+b.getNaziv());
		
		return new ResponseEntity<>(b, HttpStatus.OK);
	}
	
}
