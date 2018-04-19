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
import com.ISAProjekat.model.Rekvizit;
import com.ISAProjekat.service.KorisnikService;
import com.ISAProjekat.service.RekvizitService;

@RestController
@RequestMapping(value = "/rekvizitController")
public class RekvizitController {

	@Autowired
	private RekvizitService rekvizitService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="dodajRekvizit",method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Rekvizit> addRekvizit(@RequestBody Rekvizit rekvizit,HttpServletRequest request){
		//Rekvizit r = rekvizitService.findById(rekvizit.getId());

		Korisnik kor = (Korisnik)request.getSession().getAttribute("aktivanKorisnik");
		Korisnik k = korisnikService.findKorisnikByEmail(kor.getEmail());
		rekvizit.setRezervisan(false);
		rekvizit.setKorisnik(k);
		Rekvizit r =rekvizitService.save(rekvizit );
		Rekvizit iz_baze = rekvizitService.findById(rekvizit.getId());
			
		
			
		
			//rekvizitService.save(requestRekvizit);
		korisnikService.findKorisnikByEmail(k.getEmail()).getRekviziti().add(iz_baze);
			
			
			
		korisnikService.save(korisnikService.findKorisnikByEmail(k.getEmail()));
			
		
	
		return new ResponseEntity<>(r, HttpStatus.OK);
	}	
	
	@RequestMapping(value="getRekviziti", method = RequestMethod.GET)
	public ResponseEntity<List<Rekvizit>> getRekviziti() {
		System.out.println("\nUzimam rekvizit ..");
		List<Rekvizit> rr = rekvizitService.findAll();
		return new ResponseEntity<>(rr, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/findClickedRekvizit", method = RequestMethod.POST)
	public ResponseEntity<Rekvizit>findSelectedBioskop(@RequestBody String data, HttpServletRequest request){
		
		System.out.println(data);
		data = data.replaceAll("%22", "");
		System.out.println("NOVI DATA : "+data);
		data = data.replace("id=", "");
		System.out.println("NOVI DATA : "+data);
		
		Long id = Long.parseLong(data,10);
		
		
		
		List<Rekvizit> rekviziti = rekvizitService.findAll();
		Rekvizit ret=null;
		for(Rekvizit b: rekviziti){
			if(b.getId().compareTo(id)==0){
				ret = b;
				context.setAttribute("rekvizitIzmena", ret);
			}
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getSelectedRekvizit", method = RequestMethod.GET)
	public ResponseEntity<Rekvizit>getSelectedRekvizit(HttpServletRequest request){
		
		Rekvizit b = null;
		b = (Rekvizit) context.getAttribute("rekvizitIzmena");
		System.out.println("\nSALJE NA PROFIL: "+b.getNaziv());
		
		return new ResponseEntity<>(b, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/izmeni", method = RequestMethod.PUT)
	public Rekvizit izmeni(@RequestBody Rekvizit requestRekvizit){
		
		
	
		//System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Rekvizit iz_baze = rekvizitService.findById(requestRekvizit.getId());
		
		System.out.println("\nNAZIV MENJANOG "+requestRekvizit.getNaziv());
		System.out.println("\nID MENJANOG "+requestRekvizit.getId());
		System.out.println("\n OPIS MENJANOG "+requestRekvizit.getOpis());
		
		System.out.println("\nMENJAM rekvizit: "+iz_baze.getNaziv());
		rekvizitService.findById(requestRekvizit.getId()).setOpis(requestRekvizit.getOpis());
		rekvizitService.findById(requestRekvizit.getId()).setCena(requestRekvizit.getCena());
		rekvizitService.findById(requestRekvizit.getId()).setNaziv(requestRekvizit.getNaziv());
		rekvizitService.findById(requestRekvizit.getId()).setSlika(requestRekvizit.getSlika());
		
		System.out.println("\n naziv promenjenog "+rekvizitService.findById(requestRekvizit.getId()).getNaziv());
		context.setAttribute("rekvizitIzmena", rekvizitService.findById(requestRekvizit.getId()));
		rekvizitService.save(rekvizitService.findById(requestRekvizit.getId()));
		return rekvizitService.findById(requestRekvizit.getId());
	
	}//kraj izmene rekvizita
	
	//brisanje rekvizita
	@RequestMapping(value="/obrisi", method= RequestMethod.DELETE)
	public boolean obrisi(@RequestBody Rekvizit data){
		Rekvizit iz_baze = rekvizitService.findById(data.getId());
		System.out.println(data);
	/*	data = data.replaceAll("%22", "");
		System.out.println("NOVI DATA : "+data);
		data = data.replace("id=", "");
		System.out.println("NOVI DATA : "+data);
		*/
		//Long id = Long.parseLong(data.getId(),10);
		
		
		
		
	//	List<Projekcija> projekcije = projekcijaService.findAll();
		//projekcijaService.delete(id);
		 boolean prosao = rekvizitService.delete(data.getId());
		return prosao;
	}
	
	
	@RequestMapping(value = "/odjava", method = RequestMethod.GET)
	public boolean odjava(HttpServletRequest request) {
		request.getSession().invalidate();
		return true;
	}
	


	@RequestMapping(value = "/rezervisi", method = RequestMethod.PUT)
	public Rekvizit rezervisi(@RequestBody Rekvizit requestRekvizit,HttpServletRequest request){
		
		
		 Korisnik kor = (Korisnik)request.getSession().getAttribute("aktivanKorisnik");
		//System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Rekvizit iz_baze = rekvizitService.findById(requestRekvizit.getId());
		
		if(!(iz_baze.isRezervisan())){
			iz_baze.setRezervisan(true);
			Korisnik k = korisnikService.findKorisnikByEmail(kor.getEmail());
			System.out.println("\n Korisnik "+k.getEmail());
			System.out.println("\nNAZIV MENJANOG "+requestRekvizit.getNaziv());
			System.out.println("\nID MENJANOG "+requestRekvizit.getId());
			
			
			iz_baze.setKorisnik(k);
			//rekvizitService.save(requestRekvizit);
			korisnikService.findKorisnikByEmail(kor.getEmail()).getRekviziti().add(iz_baze);
			
			
			
			korisnikService.save(korisnikService.findKorisnikByEmail(kor.getEmail()));
			
		
			//kor.getRekviziti().add(rekvizitService.findById(requestRekvizit.getId()));
			System.out.println("\n OPIS MENJANOG "+requestRekvizit.getOpis());
			//rekvizitService.findById(requestRekvizit.getId()).setKorisnik(k);
		/*	System.out.println("\nMENJAM rekvizit: "+iz_baze.getNaziv());
			rekvizitService.findById(requestRekvizit.getId()).setOpis(requestRekvizit.getOpis());
			rekvizitService.findById(requestRekvizit.getId()).setCena(requestRekvizit.getCena());
			rekvizitService.findById(requestRekvizit.getId()).setNaziv(requestRekvizit.getNaziv());
			rekvizitService.findById(requestRekvizit.getId()).setSlika(requestRekvizit.getSlika());
		*/
			
			System.out.println("\n naziv promenjenog "+rekvizitService.findById(requestRekvizit.getId()).getNaziv());
			context.setAttribute("rekvizitIzmena", rekvizitService.findById(requestRekvizit.getId()));
			rekvizitService.save(rekvizitService.findById(iz_baze.getId()));
			
		}//ako je rezervisan nista ne radi
		else{
			System.out.println("\nREKVIZIT JE VEC REZERVISAN");
		}
		return rekvizitService.findById(requestRekvizit.getId());
	
	}
	
	@RequestMapping(value="getNerezervisaniRekviziti", method = RequestMethod.GET)
	public ResponseEntity<List<Rekvizit>> 	getNerezervisaniRekviziti() {
		System.out.println("\nUzimam rekvizite ..");
		List<Rekvizit> rr = rekvizitService.findAll();
		List<Rekvizit> nerezervisani = new ArrayList<Rekvizit>();
		for(Rekvizit r : rr){
			if(!(r.isRezervisan())){
				
				nerezervisani.add(r);
				System.out.println("\nUzimam rekvizite ..1" +r.getNaziv() );	
			}
		}
		return new ResponseEntity<>(nerezervisani, HttpStatus.OK);
	}
	
}
