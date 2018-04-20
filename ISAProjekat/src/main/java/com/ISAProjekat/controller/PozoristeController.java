package com.ISAProjekat.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.ISAProjekat.model.Korisnik;
import com.ISAProjekat.model.Ocena;
import com.ISAProjekat.model.PozorisnaSala;
import com.ISAProjekat.model.Pozoriste;
import com.ISAProjekat.model.Projekcija;
import com.ISAProjekat.model.Sala;
import com.ISAProjekat.service.OcenaService;
import com.ISAProjekat.service.PozorisnaSalaService;
import com.ISAProjekat.service.PozoristeService;
import com.ISAProjekat.service.ProjekcijaService;

@RestController
@RequestMapping("/pozoristeController")
public class PozoristeController {
	@Autowired
	private PozoristeService pozoristeService;
	
	@Autowired
	private PozorisnaSalaService pozorisnaSalaService;
	
	@Autowired
	private PozorisnaSalaService pozSalaService;
	
	@Autowired
	private ProjekcijaService projekcijaService;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private OcenaService ocenaService;
	
	@RequestMapping(value="/getPozorista", method = RequestMethod.GET)
	public ResponseEntity<List<Pozoriste>> getPozorista(){
		List<Pozoriste> pozorista = pozoristeService.findAll();
		
	//	Pozoriste p1 = new Pozoriste("Pozoriste1","Stefana Milanovica BB, Novi Sad","Novi bioskop u gradu, 200 sala, besplatni prikazi prvih 300 godina.",0);
	//	Pozoriste p2 = new Pozoriste("Pozoriste2","Jovice Cubrica 142, Novi Sad","Novo pozoriste u gradu, 1500 sala, besplatni prikazi prvih 35 godina.",0);
		
	//	Pozoriste p3 = new Pozoriste("Pozoriste3","Aleksandra Lupica 33, Novi Sad","Novo pozoriste u gradu, 1500 sala, besplatni prikazi prvih 35 godina.",0);
		
	//	Pozoriste p4 = new Pozoriste("Pozoriste4","Jefimije Zivkovic 14, Novi Sad","Novo pozoriste u gradu, 1500 sala, besplatni prikazi prvih 35 godina.", 0);
		
				
	//Sala s1 = new Sala("Sala1",)
	//Sala s2 = new Sala("Sala 2", pozorista.get(0).getId());
				
					
	/*	Projekcija pr1 = new Projekcija("Hamlet", "Tragedija", "Vilijam Sekspir", "3:00", 0, 0, "Ima nesto trulo u drzavi Danskoj.", 
							300.00, "Piter Stelers, Dzordz C. Scott", sale.get(0).getId(), sale.get(0).getNaziv());
					
					Projekcija pr2 = new Projekcija("Makbet", "Tragedija", "Vilijam Sekspir", "2:23", 0, 0, "Zivot je samo senka koja hoda, kukavni glumac sto na pozornici Sat-dva se puci i razbacuje...", 
							350.00, "Takashi Shimura", sale.get(1).getId(),sale.get(1).getNaziv());
					
					projekcijaService.save(pr1);
					projekcijaService.save(pr2);*/
		return new ResponseEntity<>(pozorista, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/findClickedPozoriste", method = RequestMethod.POST)
	public ResponseEntity<Pozoriste>findSelectedPozoriste(@RequestBody String data, HttpServletRequest request){
		
		System.out.println(data);
		data = data.replaceAll("%22", "");
		System.out.println("NOVI DATA : "+data);
		data = data.replace("id=", "");
		System.out.println("NOVI DATA : "+data);
		
		Long id = Long.parseLong(data,10);
		
		
		
		List<Pozoriste> pozorista = pozoristeService.findAll();
		Pozoriste ret=null;
		for(Pozoriste p: pozorista){
			if(p.getId().compareTo(id)==0){
				ret = p;
				request.getSession().setAttribute("pozoristeProfil", ret);
				//context.setAttribute("pozoristeProfil", ret);
			}
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getSelectedPozoriste", method = RequestMethod.GET)
	public ResponseEntity<Pozoriste>getSelectedPozoriste(HttpServletRequest request){
		
		Pozoriste b = null;
		//b = (Pozoriste) context.getAttribute("pozoristeProfil");
		b = (Pozoriste) request.getSession().getAttribute("pozoristeProfil");
		//System.out.println("SALJE NA PROFIL: "+b.getAdresa());
		
		return new ResponseEntity<>(b, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getSelectedPozoristeSale", method = RequestMethod.GET)
	public ResponseEntity<Set<PozorisnaSala>> getSelectedPozoristeSale(HttpServletRequest request){
		
		List<PozorisnaSala> sale = pozSalaService.findAll();
		Pozoriste b = (Pozoriste) request.getSession().getAttribute("pozoristeProfil");
		pozoristeService.findPozoristeById(b.getId()).setPozorisneSale(new HashSet<PozorisnaSala>());
		
		for(PozorisnaSala s: sale){
			if(s.getPozoriste().getId().compareTo(pozoristeService.findPozoristeById(b.getId()).getId())==0) {
				pozoristeService.findPozoristeById(b.getId()).getPozorisneSale().add(s);
			}
		}
		pozoristeService.save(pozoristeService.findPozoristeById(b.getId()));
		request.getSession().setAttribute("pozoristeProfil", pozoristeService.findPozoristeById(b.getId()));
		
		
		return new ResponseEntity<>(pozoristeService.findPozoristeById(b.getId()).getPozorisneSale(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getSelectedPozoristeProjekcije", method = RequestMethod.GET)
	public ResponseEntity<List<Projekcija>> getSelectedPozoristeProjekcije(HttpServletRequest request){
		
		List<Projekcija> projekcije = projekcijaService.findAll();
		//Pozoriste b = (Pozoriste) context.getAttribute("pozoristeProfil");
		Pozoriste b = (Pozoriste) request.getSession().getAttribute("pozoristeProfil");
		List<PozorisnaSala> poz_sale = pozorisnaSalaService.findAll();
		for(PozorisnaSala s: poz_sale){
			pozorisnaSalaService.findSalaById(s.getId()).setProjekcije(new HashSet<Projekcija>());
		}
		
		
		ArrayList<Projekcija> ret = new ArrayList<Projekcija>();	
		
		for(Projekcija p: projekcije){
			for(PozorisnaSala s: b.getPozorisneSale()){
				if(p.getPoz_sala()!=null && p.getPoz_sala().getId().compareTo(s.getId())==0){
					pozorisnaSalaService.findSalaById(s.getId()).getProjekcije().add(p);
					pozorisnaSalaService.save(pozorisnaSalaService.findSalaById(s.getId()));
					ret.add(p);
				}
			}
		}
		
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/editPozoriste", method= RequestMethod.PUT)
	public ResponseEntity<Pozoriste> editPozoriste(@RequestBody Pozoriste requestPozoriste, HttpServletRequest request)
	{								
		Pozoriste iz_baze = pozoristeService.findPozoristeById(requestPozoriste.getId());
		
		System.out.println("NAZIV MENJANOG "+requestPozoriste.getNaziv());
		System.out.println("ADRESA MENJANOG "+requestPozoriste.getAdresa());
		System.out.println("ID MENJANOG "+requestPozoriste.getId());
		System.out.println("OPIS MENJANOG "+requestPozoriste.getOpis());
		
		System.out.println("MENJAM POZORISTE: "+iz_baze.getNaziv());
		pozoristeService.findPozoristeById(requestPozoriste.getId()).setOpis(requestPozoriste.getOpis());
		pozoristeService.findPozoristeById(requestPozoriste.getId()).setAdresa(requestPozoriste.getAdresa());
		pozoristeService.findPozoristeById(requestPozoriste.getId()).setNaziv(requestPozoriste.getNaziv());
		
		pozoristeService.save(pozoristeService.findPozoristeById(requestPozoriste.getId()));
		//context.setAttribute("pozoristeProfil", pozoristeService.findPozoristeById(requestPozoriste.getId()));
		request.getSession().setAttribute("pozoristeProfil", pozoristeService.findPozoristeById(requestPozoriste.getId()));
		
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
		List<Ocena> ocene = ocenaService.findAll();
		for(Ocena o: ocene){
			if(o.getProjekcija()!=null && o.getProjekcija().getId().compareTo(id)==0){
				ocenaService.delete(o.getId());
			}
		}
		
		projekcijaService.delete(id);
		
		return new ResponseEntity <>(projekcije.get(0), HttpStatus.OK);
	}
	
	@RequestMapping(value="/findProjekciju", method = RequestMethod.POST)
	public ResponseEntity<Projekcija>findSelectedProjekcija(@RequestBody String data, HttpServletRequest request){
		
		System.out.println(data);
		data = data.replaceAll("%22", "");
		System.out.println("NOVI DATA : "+data);
		data = data.replace("id=", "");
		System.out.println("NOVI DATA : "+data);
		
		Long id = Long.parseLong(data,10);
		
		
		
		List<Projekcija> projekcije = projekcijaService.findAll();
		Projekcija ret=null;
		for(Projekcija p: projekcije){
			if(p.getId().compareTo(id)==0){
				ret = p;
			}
		}
		
		//context.setAttribute("aktivnaProjekcija", ret);
		request.getSession().setAttribute("aktivnaProjekcijaPoz", ret);
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	//Registracija pozorista s
		@RequestMapping(value = "/registracijaPozorista", method = RequestMethod.POST)
		public ResponseEntity<Pozoriste> registracijaPozorista(@RequestBody Pozoriste requestPozoriste){
			
			//System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
			Pozoriste preuzetBioskop = new Pozoriste(requestPozoriste.getNaziv(),requestPozoriste.getAdresa(),requestPozoriste.getOpis(),0 ,0 ,0);
			
			List<Pozoriste> lk = pozoristeService.findAll() ;		
			//ako je baza prazna samo ga dodaj bez provere 
			if(lk.isEmpty()){
				if(!preuzetBioskop.getNaziv().isEmpty() && !preuzetBioskop.getOpis().isEmpty() && !preuzetBioskop.getAdresa().isEmpty()) 	
					
				{
					System.out.println("\nProsao 1");	
					preuzetBioskop.setPozorisneSale(new HashSet<PozorisnaSala>());
					pozoristeService.save(preuzetBioskop);

					context.setAttribute("regPozoriste", preuzetBioskop);
					return new ResponseEntity<Pozoriste>(preuzetBioskop, HttpStatus.OK);
				}else{
					System.out.println("\n NIJE Prosao 1");
					return new ResponseEntity<Pozoriste>(preuzetBioskop, HttpStatus.BAD_REQUEST);				
				}			
			}		
			boolean prolazi = true;
			for(Pozoriste k : lk){
				if((k.getNaziv().equals(preuzetBioskop.getNaziv()))){
					prolazi =false;
					System.out.println("\n ZAVRSI");
					return new ResponseEntity<Pozoriste>(preuzetBioskop, HttpStatus.BAD_REQUEST);
				}else{
					
					prolazi = true;
					//System.out.println("\n UPOREDI "+k.getEmail() +" i "+ preuzetBioskop.getEmail()+"\n");
				}
			}
			if(!preuzetBioskop.getNaziv().isEmpty() && !preuzetBioskop.getOpis().isEmpty() && !preuzetBioskop.getAdresa().isEmpty()) 	
					
				{
					System.out.println("\nProsao2");
					preuzetBioskop.setPozorisneSale(new HashSet<PozorisnaSala>());
					pozoristeService.save(preuzetBioskop);

					context.setAttribute("regPozoriste", preuzetBioskop);
					return new ResponseEntity<Pozoriste>(preuzetBioskop, HttpStatus.OK);
				}
					System.out.println("\nEmail zauzet!");
					System.out.println("\n NIJE Prosao 2");
					return new ResponseEntity<Pozoriste>(preuzetBioskop, HttpStatus.BAD_REQUEST);
			
		}
		
		
		
		
		@RequestMapping(value="/getPozoristeZaReg", method = RequestMethod.GET)
		public ResponseEntity<Pozoriste>getBioskopZaReg(HttpServletRequest request){
			
			Pozoriste b = null;
			b = (Pozoriste) context.getAttribute("regPozoriste");
			System.out.println("\n Registracija sala za pozoriste: "+b.getNaziv());
			
			return new ResponseEntity<>(b, HttpStatus.OK);
		}
		
		//PREUZMI SALU DOBRU , i otkomentarisi u registraciji pozorista inicijalizaciju skupa sala !!!
		
		@RequestMapping(value = "/registracijaSala", method = RequestMethod.POST)
		public ResponseEntity<PozorisnaSala> registracijaSala(@RequestBody PozorisnaSala requestSala){
			
			Pozoriste b = null;
			b = (Pozoriste) context.getAttribute("regPozoriste");
			System.out.println("\n usao u reg sale1 "+b.getNaziv());
			//System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
			PozorisnaSala preuzetSala = new PozorisnaSala(requestSala.getNaziv());
			System.out.println("\n usao u reg sale2 "+b.getNaziv());
			
			Pozoriste bRoditelj = pozoristeService.findPozoristeById(b.getId());
			// List<Sala>sve_sale = salaService.findAll();
			System.out.println("\n usao u reg sale3 "+b.getNaziv());
			 
				for(PozorisnaSala s: bRoditelj.getPozorisneSale()){
					if(s.getNaziv().equals(preuzetSala.getNaziv())){
						System.out.println("\n ****** IMA SALA SA TIM IMENOM!");
						return new ResponseEntity<PozorisnaSala>(preuzetSala, HttpStatus.BAD_REQUEST);
						
					}
					
				}
				
				System.out.println("\n usao u reg sale4 "+b.getNaziv());
				//OVDE SAMO PRILAGOI !!!
				preuzetSala.setPozoriste(bRoditelj);
				pozorisnaSalaService.save(preuzetSala);
				pozoristeService.findPozoristeById(b.getId()).getPozorisneSale().add(preuzetSala);
					
					
				
				pozoristeService.save(pozoristeService.findPozoristeById(b.getId()));
				
				System.out.println("\n usao u reg sale1 "+b.getNaziv());
				
				return new ResponseEntity<PozorisnaSala>(preuzetSala, HttpStatus.OK);
				//return new ResponseEntity<Sala>(preuzetSala, HttpStatus.BAD_REQUEST);
			//ako je baza prazna samo ga dodaj bez provere 
		
		}
		
		@RequestMapping(value="/setSelektovanuProjekciju", method= RequestMethod.POST)
		public ResponseEntity<Projekcija> setEditovanuProjekciju(@RequestBody String data, HttpServletRequest request){
			
			System.out.println(data);
			data = data.replaceAll("%22", "");
			System.out.println("NOVI DATA : "+data);
			data = data.replace("id=", "");
			System.out.println("NOVI DATA : "+data);
			
			Long id = Long.parseLong(data,10);
			
			//context.setAttribute("editovanaProjekcija", projekcijaService.findProjekcijaById(id));
			request.getSession().setAttribute("editovanaProjekcijaPoz", projekcijaService.findProjekcijaById(id));
			return new ResponseEntity <>(projekcijaService.findProjekcijaById(id), HttpStatus.OK);
		}
		
		@RequestMapping(value="/getSelektovanuProjekciju", method= RequestMethod.GET)
		public ResponseEntity<Projekcija> getEditovanuProjekciju(HttpServletRequest request){						
			//Projekcija p = (Projekcija) context.getAttribute("editovanaProjekcija");			
			Projekcija p = (Projekcija) request.getSession().getAttribute("editovanaProjekcijaPoz");
			return new ResponseEntity <>(p, HttpStatus.OK);
		}
		
		@RequestMapping(value="/oceniPozoriste", method= RequestMethod.PUT)
		public Pozoriste oceniPozoriste(@RequestBody String data, HttpServletRequest request)
		{								
			System.out.println(data);
			data = data.replaceAll("%22", "");
			System.out.println("NOVI DATA : "+data);
			data = data.replace("id=", "");
			System.out.println("NOVI DATA : "+data);
			
			boolean glasao = false;
			int vrednost = Integer.parseInt(data);
			//Bioskop b = (Bioskop) context.getAttribute("bioskopProfil");
			Pozoriste b = (Pozoriste) request.getSession().getAttribute("pozoristeProfil");
			Korisnik k = (Korisnik)request.getSession().getAttribute("aktivanKorisnik");
			
			if(b.getOcene()!=null){
				for(Ocena o : pozoristeService.findPozoristeById(b.getId()).getOcene()){
					if(o.getKorisnik().getId().compareTo(k.getId())==0){
						glasao = true;		
						o.setVrednost(vrednost);
						pozoristeService.save(pozoristeService.findPozoristeById(b.getId()));
					}
				}
			}
			
			if(glasao){
				System.out.println("Korisnik je vec ocenio, menja se stara vrednost");
				
				int ukupan_zbir =0 ;
				double prosecna_ocena;
				
				for(Ocena o : pozoristeService.findPozoristeById(b.getId()).getOcene()){			
					ukupan_zbir += o.getVrednost();
				}
				
				prosecna_ocena = (double)ukupan_zbir/pozoristeService.findPozoristeById(b.getId()).getBroj_glasova();
				
				pozoristeService.findPozoristeById(b.getId()).setProsecna_ocena(prosecna_ocena);
				pozoristeService.save(pozoristeService.findPozoristeById(b.getId()));
				//context.setAttribute("bioskopProfil", bioskopService.findBioskopById(b.getId()));
				request.getSession().setAttribute("bioskopProfil", pozoristeService.findPozoristeById(b.getId()));
				return pozoristeService.findPozoristeById(b.getId());
			}
			
			
			Ocena nova_ocena = new Ocena(k, null, pozoristeService.findPozoristeById(b.getId()), null, vrednost);
			ocenaService.save(nova_ocena);
			
			pozoristeService.findPozoristeById(b.getId()).getOcene().add(nova_ocena);
			
			pozoristeService.findPozoristeById(b.getId()).setBroj_glasova(pozoristeService.findPozoristeById(b.getId()).getBroj_glasova()+1);
			
			int ukupan_zbir =0 ;
			double prosecna_ocena;
			
			for(Ocena o : pozoristeService.findPozoristeById(b.getId()).getOcene()){			
				ukupan_zbir += o.getVrednost();
			}
			System.out.println("UKUPAN ZBIR JE: "+ukupan_zbir);
			
			prosecna_ocena = (double)ukupan_zbir/pozoristeService.findPozoristeById(b.getId()).getBroj_glasova();
			pozoristeService.findPozoristeById(b.getId()).setProsecna_ocena(prosecna_ocena);
			pozoristeService.save(pozoristeService.findPozoristeById(b.getId()));
			
			//context.setAttribute("bioskopProfil", bioskopService.findBioskopById(b.getId()));
			request.getSession().setAttribute("bioskopProfil", pozoristeService.findPozoristeById(b.getId()));
			
			return pozoristeService.findPozoristeById(b.getId());
		}
		
		
		@RequestMapping(value="/oceniProjekciju", method= RequestMethod.PUT)
		public Projekcija oceniProjekciju(@RequestBody String data, HttpServletRequest request)
		{								
			System.out.println(data);
			data = data.replaceAll("%22", "");
			System.out.println("NOVI DATA : "+data);
			data = data.replace("id=", "");
			System.out.println("NOVI DATA : "+data);
			
			boolean glasao = false;
			int vrednost = Integer.parseInt(data);
			//Projekcija p = (Projekcija) context.getAttribute("aktivnaProjekcija");
			Projekcija p = (Projekcija) request.getSession().getAttribute("aktivnaProjekcija");
			Korisnik k = (Korisnik)request.getSession().getAttribute("aktivanKorisnik");
			
			for(Ocena o: projekcijaService.findProjekcijaById(p.getId()).getOcene()){
				if(o.getKorisnik().getId().compareTo(k.getId())==0){
					glasao = true;		
					o.setVrednost(vrednost);
					projekcijaService.save(projekcijaService.findProjekcijaById(p.getId()));
				}
			}
			
			if(glasao){
				System.out.println("Korisnik je vec ocenio, menja se stara vrednost");
				
				int ukupan_zbir =0 ;
				double prosecna_ocena;
				
				for(Ocena o : projekcijaService.findProjekcijaById(p.getId()).getOcene()){			
					ukupan_zbir += o.getVrednost();
				}
				
				prosecna_ocena = (double)ukupan_zbir/projekcijaService.findProjekcijaById(p.getId()).getBroj_glasova();
				
				projekcijaService.findProjekcijaById(p.getId()).setProsecna_ocena(prosecna_ocena);
				projekcijaService.save(projekcijaService.findProjekcijaById(p.getId()));
				//context.setAttribute("aktivnaProjekcija", projekcijaService.findProjekcijaById(p.getId()));
				request.getSession().setAttribute("aktivnaProjekcija", projekcijaService.findProjekcijaById(p.getId()));
				return projekcijaService.findProjekcijaById(p.getId());
			}
			
			Ocena nova_ocena = new Ocena(k,null,null,projekcijaService.findProjekcijaById(p.getId()),vrednost);
			ocenaService.save(nova_ocena);
			
			
			projekcijaService.findProjekcijaById(p.getId()).getOcene().add(nova_ocena);
			
			projekcijaService.findProjekcijaById(p.getId()).setBroj_glasova(projekcijaService.findProjekcijaById(p.getId()).getBroj_glasova()+1);
			
			int ukupan_zbir =0 ;
			double prosecna_ocena;
			
			System.out.println("BROJ");
			
			for(Ocena o : projekcijaService.findProjekcijaById(p.getId()).getOcene()){			
				ukupan_zbir += o.getVrednost();
			}
			System.out.println("UKUPAN ZBIR JE: "+ukupan_zbir);
			
			prosecna_ocena = (double)ukupan_zbir/projekcijaService.findProjekcijaById(p.getId()).getBroj_glasova();
			projekcijaService.findProjekcijaById(p.getId()).setProsecna_ocena(prosecna_ocena);
			projekcijaService.save(projekcijaService.findProjekcijaById(p.getId()));
			
			//context.setAttribute("aktivnaProjekcija", projekcijaService.findProjekcijaById(p.getId()));
			request.getSession().setAttribute("aktivnaProjekcija", projekcijaService.findProjekcijaById(p.getId()));
			
			return projekcijaService.findProjekcijaById(p.getId());
		}
		
		@RequestMapping(value="/getSlicneProjekcije", method= RequestMethod.GET) 
		public List<Projekcija> getSlicneProjekcije(HttpServletRequest request){
			List<Projekcija> projekcije = projekcijaService.findAll();
			//Projekcija p = (Projekcija) context.getAttribute("aktivnaProjekcija");
			Projekcija p = (Projekcija) request.getSession().getAttribute("aktivnaProjekcija");
			ArrayList<Projekcija> ret = new ArrayList<Projekcija>();
			for(Projekcija x: projekcije){
				if(x.getNaziv().equals(p.getNaziv())){
					ret.add(x);
				}
			}
			return ret;
		}
}
