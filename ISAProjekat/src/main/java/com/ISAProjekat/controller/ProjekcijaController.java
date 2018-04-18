package com.ISAProjekat.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ISAProjekat.model.Bioskop;
import com.ISAProjekat.model.Korisnik;
import com.ISAProjekat.model.Projekcija;
import com.ISAProjekat.model.Sala;
import com.ISAProjekat.service.ProjekcijaService;

@RestController
@RequestMapping("/projekcija")
public class ProjekcijaController {
	
	@Autowired
	private ProjekcijaService projekcijaService;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="/getProjekcije", method = RequestMethod.GET)
	public ResponseEntity<List<Projekcija>>getProjekcije(){
		List<Projekcija> projekcije = projekcijaService.findAll();
		return new ResponseEntity<>(projekcije, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dodajProjekciju", method= RequestMethod.POST)
	public Projekcija dodajProjekciju(@RequestBody Projekcija requestProjekcija)
	{		
		System.out.println("\n\nPoslati podaci: \n"+requestProjekcija.getNaziv()+"\n"+requestProjekcija.getIme_reditelja()+"\n");	
		
		Bioskop b = (Bioskop) context.getAttribute("bioskopProfil");
		Sala s = (Sala) context.getAttribute("setovana_sala");
		
		Projekcija nova_p = new Projekcija(requestProjekcija.getNaziv(),requestProjekcija.getZarn(),requestProjekcija.getIme_reditelja(),
				requestProjekcija.getTrajanje(),requestProjekcija.getTermin_od(), requestProjekcija.getTermin_do(), 0,0,requestProjekcija.getOpis(),requestProjekcija.getCena(),requestProjekcija.getSpisak_glumaca(),s);
		
		projekcijaService.save(nova_p);
		
		System.out.println("DODAO PROJEKCIJU");
		return nova_p;
	}
	
	@RequestMapping(value="/setujSala", method= RequestMethod.POST)
	public Sala setujSalu(@RequestBody Sala requestSala)
	{				
		context.setAttribute("setovana_sala", requestSala);
		System.out.println("SETOVAO SALU");
		return requestSala;
	}
}
