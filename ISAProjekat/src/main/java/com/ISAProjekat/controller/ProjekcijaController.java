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
import com.ISAProjekat.model.Ocena;
import com.ISAProjekat.model.Projekcija;
import com.ISAProjekat.model.Sala;
import com.ISAProjekat.service.OcenaService;
import com.ISAProjekat.service.ProjekcijaService;

@RestController
@RequestMapping("/projekcija")
public class ProjekcijaController {
	
	@Autowired
	private ProjekcijaService projekcijaService;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	OcenaService ocenaService;
	
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
				requestProjekcija.getTrajanje(),requestProjekcija.getTermin_od(), requestProjekcija.getTermin_do(), 0,0,requestProjekcija.getOpis(),requestProjekcija.getCena(),requestProjekcija.getSpisak_glumaca(),s, null);
		
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
	
	@RequestMapping(value="/editProjekciju", method= RequestMethod.PUT)
	public ResponseEntity<Projekcija> editBioskop(@RequestBody Projekcija requestProjekcija)
	{								
		Projekcija iz_baze = projekcijaService.findProjekcijaById(requestProjekcija.getId());
		
		System.out.println("NAZIV MENJANOG "+requestProjekcija.getNaziv());
		System.out.println("REZISER MENJANOG "+requestProjekcija.getIme_reditelja());
		System.out.println("ID MENJANOG "+requestProjekcija.getId());
		System.out.println("OPIS MENJANOG "+requestProjekcija.getOpis());
		
		System.out.println("MENJAM PROJEKCIJU: "+iz_baze.getNaziv());
		
		Sala s = (Sala) context.getAttribute("setovana_sala");
		
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setNaziv(requestProjekcija.getNaziv());
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setZarn(requestProjekcija.getZarn());
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setIme_reditelja(requestProjekcija.getIme_reditelja());
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setTrajanje(requestProjekcija.getTrajanje());
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setOpis(requestProjekcija.getOpis());
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setCena(requestProjekcija.getCena());
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setSpisak_glumaca(requestProjekcija.getSpisak_glumaca());
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setSala(s);
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setTermin_od(requestProjekcija.getTermin_od());
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setTermin_do(requestProjekcija.getTermin_do());
		
		
		projekcijaService.save(projekcijaService.findProjekcijaById(requestProjekcija.getId()));
		return null;
	}
}
