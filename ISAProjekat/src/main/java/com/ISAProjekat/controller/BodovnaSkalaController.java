package com.ISAProjekat.controller;

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
import com.ISAProjekat.model.Rekvizit;
import com.ISAProjekat.service.BodovnaSkalaService;

@RestController
@RequestMapping("/bodovnaSkalaController")
public class BodovnaSkalaController {

	@Autowired
	private BodovnaSkalaService bodovnaSkalaService;
	
	@Autowired
	ServletContext context;
	

	@RequestMapping(value="/getBodovnaSkalaVrednosti", method = RequestMethod.GET)
	public ResponseEntity<BodovnaSkala>getBodovnaSkalaVrednosti(HttpServletRequest request){
		
		BodovnaSkala b = new BodovnaSkala();
	//	b = (BodovnaSkala) context.getAttribute("rekvizitIzmena");
		//System.out.println("\nSALJE NA PROFIL: "+b.getNaziv());
		 b=  bodovnaSkalaService.findById((long) 1);
		 
		return new ResponseEntity<>(b, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeni", method = RequestMethod.PUT)
	public BodovnaSkala izmeni(@RequestBody BodovnaSkala bodovnaSkalaRekvizit){

		//System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		BodovnaSkala iz_baze = bodovnaSkalaService.findById((long)1);
		
		System.out.println("\n*** bronzani prosledjen " + bodovnaSkalaRekvizit.getZlatniBod());
		bodovnaSkalaService.findById((long)1).setBronzaniBod(bodovnaSkalaRekvizit.getBronzaniBod());
		bodovnaSkalaService.findById((long)1).setSrebrniBod(bodovnaSkalaRekvizit.getSrebrniBod());
		bodovnaSkalaService.findById((long)1).setZlatniBod(bodovnaSkalaRekvizit.getZlatniBod());
		System.out.println("\n MENJAM BODOVNU SKALU! ");
		
		//System.out.println("\n naziv promenjenog "+rekvizitService.findById(requestRekvizit.getId()).getNaziv());
		//context.setAttribute("rekvizitIzmena", rekvizitService.findById(requestRekvizit.getId()));
		bodovnaSkalaService.save(bodovnaSkalaService.findById((long)1));
		return bodovnaSkalaService.findById((long) 1);
	
	}
}
