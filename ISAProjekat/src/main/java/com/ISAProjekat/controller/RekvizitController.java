package com.ISAProjekat.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ISAProjekat.model.Bioskop;
import com.ISAProjekat.model.Rekvizit;
import com.ISAProjekat.service.RekvizitService;

@RestController
@RequestMapping(value = "/rekvizitController")
public class RekvizitController {

	@Autowired
	private RekvizitService rekvizitService;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="dodajRekvizit",method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Rekvizit> addRekvizit(@RequestBody Rekvizit rekvizit){
		Rekvizit r = rekvizitService.save(rekvizit);
		return new ResponseEntity<>(r, HttpStatus.OK);
	}	
	
	@RequestMapping(value="getRekviziti", method = RequestMethod.GET)
	public ResponseEntity<List<Rekvizit>> getRekviziti() {
		System.out.println("\nUzimam rekvizit ..");
		List<Rekvizit> rr = rekvizitService.findAll();
		return new ResponseEntity<>(rr, HttpStatus.OK);
	}
	
	@RequestMapping(value = "obrisi", method = RequestMethod.DELETE)
	public ResponseEntity<Rekvizit> delete(@RequestBody Rekvizit rekvizit,@PathVariable Long id) {
		System.out.println("************\nasdasd");
		long pom;
		//Rekvizit deleted = rekvizitService.delete(id);
		Rekvizit deleted = new Rekvizit();
		for(Rekvizit r : rekvizitService.findAll()){
			if(r.getId().equals(rekvizit.getId())){
				System.out.println("\n Nasao sam rekvizit!\n");
				pom = r.getId();
				deleted = rekvizitService.delete(r.getId());
			}
			
		}
		
	//	
		return new ResponseEntity<>(deleted, HttpStatus.OK);
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
	
	
	@RequestMapping(value = "/odjava", method = RequestMethod.GET)
	public boolean odjava(HttpServletRequest request) {
		request.getSession().invalidate();
		return true;
	}
	
}
