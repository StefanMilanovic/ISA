package com.ISAProjekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ISAProjekat.model.Korisnik;
import com.ISAProjekat.model.Projekcija;
import com.ISAProjekat.service.ProjekcijaService;

@RestController
@RequestMapping("/projekcija")
public class ProjekcijaController {
	
	@Autowired
	private ProjekcijaService projekcijaService;
	
	@RequestMapping(value="getProjekcije", method = RequestMethod.GET)
	public ResponseEntity<List<Projekcija>>getProjekcije(){
		List<Projekcija> projekcije = projekcijaService.findAll();
		return new ResponseEntity<>(projekcije, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dodajProjekciju", method= RequestMethod.POST)
	public ResponseEntity<Projekcija> dodajProjekciju(@RequestBody Projekcija requestProjekcija)
	{		
		System.out.println("\n\nPoslati podaci: \n"+requestProjekcija.getNaziv()+"\n"+requestProjekcija.getIme_reditelja()+"\n");	
		
		if(requestProjekcija.getNaziv().length()!=0){
			Projekcija nova_projekcija = new Projekcija(requestProjekcija.getNaziv(),requestProjekcija.getZarn(),requestProjekcija.getIme_reditelja(),
					requestProjekcija.getTrajanje(), 0, requestProjekcija.getOpis(),requestProjekcija.getCena(), requestProjekcija.getSpisak_glumaca(), requestProjekcija.getSala());
			
					
			projekcijaService.save(nova_projekcija);
			return new ResponseEntity<Projekcija>(nova_projekcija,HttpStatus.OK);
			
		}
		
		
		return null;
	}
}
