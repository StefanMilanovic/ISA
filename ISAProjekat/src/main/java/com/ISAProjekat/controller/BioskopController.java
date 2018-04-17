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

import com.ISAProjekat.model.Bioskop;
import com.ISAProjekat.model.Projekcija;
import com.ISAProjekat.model.Sala;
import com.ISAProjekat.service.BioskopService;
import com.ISAProjekat.service.ProjekcijaService;
import com.ISAProjekat.service.SalaService;

@RestController
@RequestMapping("/bioskopController")
public class BioskopController{

	@Autowired
	private BioskopService bioskopService;
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private ProjekcijaService projekcijaService;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="/getBioskopi", method = RequestMethod.GET)
	public ResponseEntity<List<Bioskop>> getBioskopi(){
		List<Bioskop> bioskopi = bioskopService.findAll();
		List<Sala> sale = salaService.findAll();
		List<Projekcija> projekcije = projekcijaService.findAll();
		
		Bioskop b1 = new Bioskop("Bioskop1","Stefana Milanovica BB, Novi Sad","Novi bioskop u gradu, 200 sala, besplatni prikazi prvih 300 godina.",4.7);
		Bioskop b2 = new Bioskop("Bioskop2","Nadje Loncar BB, Novi Sad","Novi bioskop u gradu, 1500 sala, besplatni prikazi prvih 35 godina.",4.5);
		
		Bioskop b3 = new Bioskop("Bioskop2","Nadje Loncar BB, Novi Sad","Novi bioskop u gradu, 1500 sala, besplatni prikazi prvih 35 godina.",4.5);
		
		Bioskop b4 = new Bioskop("Bioskop2","Nadje Loncar BB, Novi Sad","Novi bioskop u gradu, 1500 sala, besplatni prikazi prvih 35 godina.", 4.5);
		
		if(bioskopi.isEmpty()){			
			
			bioskopService.save(b1);
			bioskopService.save(b2);
			bioskopService.save(b3);
			bioskopService.save(b4);
			
			bioskopi = bioskopService.findAll();
					
			System.out.println("DODATI BIOSKOPI U BAZU.");
			
			
			if(sale.isEmpty()){
				
				Sala s1 = new Sala("Sala 1", bioskopi.get(0).getId());
				Sala s2 = new Sala("Sala 2", bioskopi.get(0).getId());
				
				salaService.save(s1);
				salaService.save(s2);
				
				sale = salaService.findAll();
				
				System.out.println("DODATE SALE U BAZU.");
			
			
				if(projekcije.isEmpty()){
					
					Projekcija p1 = new Projekcija("Dr.Strangelove", "Komedija", "Stenli Kubrik", "1:30", 0, 0, "Nakon sto jedan americki general samovoljno posalje nuklearni bombarder na SSSR, predsednik SAD i ambasador SSSR-a pokusavaju da zaustave taj avion.", 
							300.00, "Piter Stelers, Dzordz C. Scott", sale.get(0).getId(), sale.get(0).getNaziv());
					
					Projekcija p2 = new Projekcija("Ziveti", "Drama", "Akira Kurosava", "2:23", 0, 0, "Birokrata pokusava da nadje smisao u svom zivotu nakon sto otkrije da ima neizleciv rak", 
							350.00, "Takashi Shimura", sale.get(1).getId(),sale.get(1).getNaziv());
					
					projekcijaService.save(p1);
					projekcijaService.save(p2);
				}
			}
		}
		return new ResponseEntity<>(bioskopi, HttpStatus.OK);
	}
	
	@RequestMapping(value="/findClickedBioskop", method = RequestMethod.POST)
	public ResponseEntity<Bioskop>findSelectedBioskop(@RequestBody String data, HttpServletRequest request){
		
		System.out.println(data);
		data = data.replaceAll("%22", "");
		System.out.println("NOVI DATA : "+data);
		data = data.replace("id=", "");
		System.out.println("NOVI DATA : "+data);
		
		Long id = Long.parseLong(data,10);
		
		
		
		List<Bioskop> bioskopi = bioskopService.findAll();
		Bioskop ret=null;
		for(Bioskop b: bioskopi){
			if(b.getId().compareTo(id)==0){
				ret = b;
				context.setAttribute("bioskopProfil", ret);
			}
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getSelectedBioskop", method = RequestMethod.GET)
	public ResponseEntity<Bioskop>getSelectedBioskop(HttpServletRequest request){
		
		Bioskop b = null;
		b = (Bioskop) context.getAttribute("bioskopProfil");
		System.out.println("SALJE NA PROFIL: "+b.getAdresa());
		
		return new ResponseEntity<>(b, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getSelectedBioskopSale", method = RequestMethod.GET)
	public ResponseEntity<List<Sala>>getSelectedBioskopSale(HttpServletRequest request){
		
		List<Sala> sale = salaService.findAll();
		Bioskop b = (Bioskop) context.getAttribute("bioskopProfil");
		
		ArrayList<Sala> ret = new ArrayList<Sala>();
	
		for(Sala s: sale){
			if(s.getId_parent().compareTo(b.getId())==0){
				ret.add(s);
			}
		}
		
		context.setAttribute("sale", ret);
		return new ResponseEntity<>(ret, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getSelectedBioskopProjekcije", method = RequestMethod.GET)
	public ResponseEntity<List<Projekcija>> getSelectedBioskopProjekcije(HttpServletRequest request){
		
		List<Projekcija> projekcije = projekcijaService.findAll();
		Bioskop b = (Bioskop) context.getAttribute("bioskopProfil");
		
		ArrayList<Sala> sale = (ArrayList<Sala>) context.getAttribute("sale");
		ArrayList<Projekcija> ret = new ArrayList<Projekcija>();	
		for(Projekcija p: projekcije){
			for(Sala s: sale){
				if(p.getSala().compareTo(s.getId())==0){
					ret.add(p);
				}
			}
		}
		
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/editBioskop", method= RequestMethod.POST)
	public ResponseEntity<Bioskop> editBioskop(@RequestBody Bioskop requestBioskop)
	{								
		Bioskop iz_baze = bioskopService.findBioskopById(requestBioskop.getId());
		
		System.out.println("NAZIV MENJANOG "+requestBioskop.getNaziv());
		System.out.println("ADRESA MENJANOG "+requestBioskop.getAdresa());
		System.out.println("ID MENJANOG "+requestBioskop.getId());
		System.out.println("OPIS MENJANOG "+requestBioskop.getOpis());
		
		System.out.println("MENJAM BIOSKOP: "+iz_baze.getNaziv());
		bioskopService.findBioskopById(requestBioskop.getId()).setOpis(requestBioskop.getOpis());
		bioskopService.findBioskopById(requestBioskop.getId()).setAdresa(requestBioskop.getAdresa());
		bioskopService.findBioskopById(requestBioskop.getId()).setNaziv(requestBioskop.getNaziv());
		
		context.setAttribute("bioskopProfil", bioskopService.findBioskopById(requestBioskop.getId()));
		
		return new ResponseEntity<>(bioskopService.findBioskopById(requestBioskop.getId()),HttpStatus.OK);
	}
	
	
}
