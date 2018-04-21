package com.ISAProjekat.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

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

import com.ISAProjekat.model.Bioskop;
import com.ISAProjekat.model.Karta;
import com.ISAProjekat.model.PozorisnaSala;
import com.ISAProjekat.model.Pozoriste;
import com.ISAProjekat.model.Projekcija;
import com.ISAProjekat.model.Sala;
import com.ISAProjekat.model.Sediste;
import com.ISAProjekat.service.BioskopService;
import com.ISAProjekat.service.KartaService;
import com.ISAProjekat.service.OcenaService;
import com.ISAProjekat.service.ProjekcijaService;
import com.ISAProjekat.service.SalaService;

@RestController
@RequestMapping("/projekcija")
public class ProjekcijaController {
	
	@Autowired
	private ProjekcijaService projekcijaService;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	OcenaService ocenaService;
	
	@Autowired
	KartaService kartaService;
	
	@Autowired
	SalaService salaService;
	
	@Autowired
	BioskopService bioskopService;
	
	@RequestMapping(value="/getProjekcije", method = RequestMethod.GET)
	public ResponseEntity<List<Projekcija>>getProjekcije(){
		List<Projekcija> projekcije = projekcijaService.findAll();
		return new ResponseEntity<>(projekcije, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dodajProjekciju", method= RequestMethod.POST)
	public Projekcija dodajProjekciju(@RequestBody Projekcija requestProjekcija, HttpServletRequest request)
	{		
		System.out.println("\n\nPoslati podaci: \n"+requestProjekcija.getNaziv()+"\n"+requestProjekcija.getIme_reditelja()+"\n");	
		
		Bioskop b = (Bioskop) request.getSession().getAttribute("bioskopProfil");
		Sala s = (Sala) context.getAttribute("setovana_sala");
		
		
		Projekcija nova_p = new Projekcija(requestProjekcija.getNaziv(),requestProjekcija.getZarn(),requestProjekcija.getIme_reditelja(),
				requestProjekcija.getTrajanje(),requestProjekcija.getTermin_od(), requestProjekcija.getTermin_do(), 0,0,requestProjekcija.getOpis(),requestProjekcija.getCena(),requestProjekcija.getSpisak_glumaca(),s, null);
		
		projekcijaService.save(nova_p);
		s = salaService.findSalaById(s.getId());
		s.getProjekcije().add(nova_p);
		salaService.save(salaService.findSalaById(s.getId()));		
		
		for(Sediste sed : s.getSedista()){
			System.out.println("DODAJEM KARTU");
			Karta k = new Karta(null,nova_p, sed, 10);
			kartaService.save(k);
		}
		
		for(Sala x : b.getSale()){
			if(x.getNaziv().equals(s.getNaziv())){
				x=s;
			}
		}
		
		bioskopService.save(bioskopService.findBioskopById(b.getId()));
		
		
		System.out.println("DODAO PROJEKCIJU");
		return nova_p;
	}
	
	@RequestMapping(value="/dodajPozProjekciju", method= RequestMethod.POST)
	public Projekcija dodajPozProjekciju(@RequestBody Projekcija requestProjekcija)
	{		
		System.out.println("\n\nPoslati podaci: \n"+requestProjekcija.getNaziv()+"\n"+requestProjekcija.getIme_reditelja()+"\n");	
		
		Pozoriste b = (Pozoriste) context.getAttribute("pozoristeProfil");
		PozorisnaSala s = (PozorisnaSala) context.getAttribute("setovana_sala");
		
		Projekcija nova_p = new Projekcija(requestProjekcija.getNaziv(),requestProjekcija.getZarn(),requestProjekcija.getIme_reditelja(),
				requestProjekcija.getTrajanje(),requestProjekcija.getTermin_od(), requestProjekcija.getTermin_do(), 0,0,requestProjekcija.getOpis(),requestProjekcija.getCena(),requestProjekcija.getSpisak_glumaca(),null, s);
		
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
	
	@RequestMapping(value="/setujPozSala", method= RequestMethod.POST)
	public PozorisnaSala setujPozSalu(@RequestBody PozorisnaSala requestSala)
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
	
	@RequestMapping(value="/editProjekcijuPoz", method= RequestMethod.PUT)
	public ResponseEntity<Projekcija> editPozoriste(@RequestBody Projekcija requestProjekcija)
	{								
		Projekcija iz_baze = projekcijaService.findProjekcijaById(requestProjekcija.getId());
		
		System.out.println("NAZIV MENJANOG "+requestProjekcija.getNaziv());
		System.out.println("REZISER MENJANOG "+requestProjekcija.getIme_reditelja());
		System.out.println("ID MENJANOG "+requestProjekcija.getId());
		System.out.println("OPIS MENJANOG "+requestProjekcija.getOpis());
		
		System.out.println("MENJAM PROJEKCIJU: "+iz_baze.getNaziv());
		
		PozorisnaSala s = (PozorisnaSala) context.getAttribute("setovana_sala");
		
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setNaziv(requestProjekcija.getNaziv());
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setZarn(requestProjekcija.getZarn());
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setIme_reditelja(requestProjekcija.getIme_reditelja());
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setTrajanje(requestProjekcija.getTrajanje());
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setOpis(requestProjekcija.getOpis());
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setCena(requestProjekcija.getCena());
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setSpisak_glumaca(requestProjekcija.getSpisak_glumaca());
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setPoz_sala(s);
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setTermin_od(requestProjekcija.getTermin_od());
		projekcijaService.findProjekcijaById(requestProjekcija.getId()).setTermin_do(requestProjekcija.getTermin_do());
		
		
		projekcijaService.save(projekcijaService.findProjekcijaById(requestProjekcija.getId()));
		return null;
	}
}
