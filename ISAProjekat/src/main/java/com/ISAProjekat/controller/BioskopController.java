package com.ISAProjekat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ISAProjekat.IsaProjekatApplication;
import com.ISAProjekat.model.Bioskop;
import com.ISAProjekat.model.Korisnik;
import com.ISAProjekat.service.BioskopService;
import com.ISAProjekat.service.KorisnikService;

@RestController
@RequestMapping("/bioskopController")
public class BioskopController{

	@Autowired
	private BioskopService bioskopService;
	
	@RequestMapping(value="/getBioskopi", method = RequestMethod.GET)
	public ResponseEntity<List<Bioskop>> getBioskopi(){
		List<Bioskop> bioskopi = bioskopService.findAll();
		
		if(bioskopi.isEmpty()){
			Bioskop b1 = new Bioskop("Bioskop1","Stefana Milanovica BB, Novi Sad","Novi bioskop u gradu, 200 sala, besplatni prikazi prvih 300 godina.",
					null,null, 4.7);
			Bioskop b2 = new Bioskop("Bioskop2","Nadje Loncar BB, Novi Sad","Novi bioskop u gradu, 1500 sala, besplatni prikazi prvih 35 godina.",
					null,null, 4.5);
			
			Bioskop b3 = new Bioskop("Bioskop2","Nadje Loncar BB, Novi Sad","Novi bioskop u gradu, 1500 sala, besplatni prikazi prvih 35 godina.",
					null,null, 4.5);
			
			Bioskop b4 = new Bioskop("Bioskop2","Nadje Loncar BB, Novi Sad","Novi bioskop u gradu, 1500 sala, besplatni prikazi prvih 35 godina.",
					null,null, 4.5);
			
			bioskopi.add(b1);
			bioskopi.add(b2);
			bioskopi.add(b3);
			bioskopi.add(b4);
			
			System.out.println("DODATO U LISTU.");
		}
		
		return new ResponseEntity<>(bioskopi, HttpStatus.OK);
	}

}
