package com.ISAProjekat.controller;

import java.util.ArrayList;
import java.util.HashSet;
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
		
		if(bioskopi.isEmpty()){					
			bioskopi = bioskopService.findAll();
					
			System.out.println("DODATI BIOSKOPI U BAZU.");
			
			
			if(sale.isEmpty()){
			//	Set sala_projekcije = new Set(null, null);
			//	Sala s1 = new Sala("Sala 1",bioskopi.get(0), new Set<Projekcija>());
			//	Sala s2 = new Sala("Sala 2", bioskopi.get(0).getId());
				
				//salaService.save(s1);
			//	salaService.save(s2);
				
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
		
		Bioskop b = (Bioskop) context.getAttribute("bioskopProfil");
		
		ArrayList<Sala> ret = new ArrayList<Sala>();
	
		for(Sala s: b.getSale()){
			ret.add(s);
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
	
	@RequestMapping(value="/editBioskop", method= RequestMethod.PUT)
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
		
		bioskopService.save(bioskopService.findBioskopById(requestBioskop.getId()));
		context.setAttribute("bioskopProfil", bioskopService.findBioskopById(requestBioskop.getId()));
		
		
		
		return null;
	}
	
	@RequestMapping(value="/obrisiProjekciju", method= RequestMethod.DELETE)
	public ResponseEntity<Projekcija> obrisiProjekciju(@RequestBody String data){
		
		System.out.println(data);
		data = data.replaceAll("%22", "");
		System.out.println("NOVI DATA : "+data);
		data = data.replace("id=", "");
		System.out.println("NOVI DATA : "+data);
		
		Long id = Long.parseLong(data,10);
		
		List<Projekcija> projekcije = projekcijaService.findAll();
		projekcijaService.delete(id);
		
		return new ResponseEntity <>(projekcije.get(0), HttpStatus.OK);
	}
	

	
	//Registracija bioskopa s
	@RequestMapping(value = "/registracijaBioskopa", method = RequestMethod.POST)
	public ResponseEntity<Bioskop> registracijaBioskopa(@RequestBody Bioskop requestBioskop){
		
		//System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Bioskop preuzetBioskop = new Bioskop(requestBioskop.getNaziv(),requestBioskop.getAdresa(),requestBioskop.getOpis());
		
		List<Bioskop> lk = bioskopService.findAll() ;		
		//ako je baza prazna samo ga dodaj bez provere 
		if(lk.isEmpty()){
			if(!preuzetBioskop.getNaziv().isEmpty() && !preuzetBioskop.getOpis().isEmpty() && !preuzetBioskop.getAdresa().isEmpty()) 	
				
			{
				System.out.println("\nProsao 1");	
				preuzetBioskop.setSale(new HashSet<Sala>());
				
				bioskopService.save(preuzetBioskop);
				context.setAttribute("regBioskop", preuzetBioskop);
				return new ResponseEntity<Bioskop>(preuzetBioskop, HttpStatus.OK);
			}else{
				System.out.println("\n NIJE Prosao 1");
				return new ResponseEntity<Bioskop>(preuzetBioskop, HttpStatus.BAD_REQUEST);				
			}			
		}		
		boolean prolazi = true;
		for(Bioskop k : lk){
			if((k.getNaziv().equals(preuzetBioskop.getNaziv()))){
				prolazi =false;
				System.out.println("\n ZAVRSI");
				return new ResponseEntity<Bioskop>(preuzetBioskop, HttpStatus.BAD_REQUEST);
			}else{
				
				prolazi = true;
				//System.out.println("\n UPOREDI "+k.getEmail() +" i "+ preuzetBioskop.getEmail()+"\n");
			}
		}
		if(!preuzetBioskop.getNaziv().isEmpty() && !preuzetBioskop.getOpis().isEmpty() && !preuzetBioskop.getAdresa().isEmpty()) 	
				
			{
				System.out.println("\nProsao2");
				preuzetBioskop.setSale(new HashSet<Sala>());
				
				bioskopService.save(preuzetBioskop);

				context.setAttribute("regBioskop", preuzetBioskop);
				return new ResponseEntity<Bioskop>(preuzetBioskop, HttpStatus.OK);
			}
				System.out.println("\nEmail zauzet!");
				System.out.println("\n NIJE Prosao 2");
				return new ResponseEntity<Bioskop>(preuzetBioskop, HttpStatus.BAD_REQUEST);
		
	}
	

	@RequestMapping(value="/getBioskopZaReg", method = RequestMethod.GET)
	public ResponseEntity<Bioskop>getBioskopZaReg(HttpServletRequest request){
		
		Bioskop b = null;
		b = (Bioskop) context.getAttribute("regBioskop");
		System.out.println("\n Registracija sala za bioskop: "+b.getNaziv());
		
		return new ResponseEntity<>(b, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/registracijaSala", method = RequestMethod.POST)
	public ResponseEntity<Sala> registracijaSala(@RequestBody Sala requestSala){
		
		Bioskop b = null;
		b = (Bioskop) context.getAttribute("regBioskop");
		//System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Sala preuzetSala = new Sala(requestSala.getNaziv());
		
		
		 Bioskop bRoditelj = bioskopService.findBioskopById(b.getId());
		 List<Sala>sve_sale = salaService.findAll();
		
		 
			for(Sala s: bRoditelj.getSale()){
				if(s.getNaziv().equals(preuzetSala.getNaziv())){
					System.out.println("\n ****** IMA SALA SA TIM IMENOM!");
					return new ResponseEntity<Sala>(preuzetSala, HttpStatus.BAD_REQUEST);
					
				}
				
			}
			preuzetSala.setBioskop(bRoditelj);
			salaService.save(preuzetSala);
			bioskopService.findBioskopById(b.getId()).getSale().add(preuzetSala);
				
				
			
			bioskopService.save(bioskopService.findBioskopById(b.getId()));
			
		
			
			return new ResponseEntity<Sala>(preuzetSala, HttpStatus.OK);
			//return new ResponseEntity<Sala>(preuzetSala, HttpStatus.BAD_REQUEST);
		//ako je baza prazna samo ga dodaj bez provere 
	
	}


}
