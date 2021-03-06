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

import com.ISAProjekat.model.Korisnik;
import com.ISAProjekat.model.Oglas;
import com.ISAProjekat.model.Ponuda;
import com.ISAProjekat.model.Poruka;
import com.ISAProjekat.service.KorisnikService;
import com.ISAProjekat.service.OglasService;
import com.ISAProjekat.service.PonudaService;
import com.ISAProjekat.service.PorukaService;

@RestController
@RequestMapping(value = "/ponudaController")
public class PonudaController {
	
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private KorisnikService korisnikService;
	

	@Autowired
	private PonudaService ponudaService;

	@Autowired
	private OglasService oglasService;


	@Autowired
	private PorukaService porukaService;
	
	@RequestMapping(value="/getPonude", method = RequestMethod.GET)
	public ResponseEntity<List<Ponuda>> getPonude() {
		List<Ponuda> ponude = ponudaService.findAll();

		return new ResponseEntity<>(ponude, HttpStatus.OK);
	}
	
	//uzimanje ponuda sa odredjenog oglasa
	@RequestMapping(value="/getPonudeOglasa", method = RequestMethod.GET)
	public ResponseEntity<List<Ponuda>> getPonudeOglasa( HttpServletRequest request) {

		Korisnik kor = (Korisnik)request.getSession().getAttribute("aktivanKorisnik");
		Korisnik k = korisnikService.findKorisnikByEmail(kor.getEmail());
		
		
		List<Ponuda> ponude = ponudaService.findAll();
		
		Oglas oglasRoditelj = null;
		oglasRoditelj = (Oglas) context.getAttribute("oglasPonuda");
		System.out.println("\n2 "+ oglasRoditelj.getId());
		
		Oglas oglasBaza = oglasService.findById(oglasRoditelj.getId());
	
		List<Ponuda> ponudeOglasa  = new ArrayList<Ponuda>();
		if(!(ponude == null)){
			for(Ponuda p : ponude){
				p.setOdRegistrovanog(false);
				System.out.println("\n5  Korisnik koji je dao ponudu -> "+ p.getKorisnik().getEmail());
				if(p.getOglas().getId() ==oglasBaza.getId()){
					
					
					if(k.getEmail().equals(p.getKorisnik().getEmail())){
						p.setOdRegistrovanog(true);
						
						System.out.println("\n Ova je od registrovanog korisnika!!!" + p.getKorisnik().getEmail());
					}
					ponudaService.save(ponudaService.findById(p.getId()));
					ponudeOglasa.add(p);
					
				}			
			}
		}else{
			System.out.println("Nema ponuda u bazi");
		}
		
		return new ResponseEntity<>(ponudeOglasa, HttpStatus.OK);
	}

	@RequestMapping(value="dodajPonudu",method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Ponuda> dodajPonudu(@RequestBody Ponuda ponuda, HttpServletRequest request){
	//	oglas.setOdobren(false);
	//	oglas.setProveren(false);
	//	Oglas noviOglas = oglasService.save(oglas);
		System.out.println("\nDodajem ponudu");
		
		Oglas oglasRoditelj = null;
		oglasRoditelj = (Oglas) context.getAttribute("oglasPonuda");
		
		Oglas oglasBaza = oglasService.findById(oglasRoditelj.getId());
		
		Korisnik kor = (Korisnik)request.getSession().getAttribute("aktivanKorisnik");
		Korisnik k = korisnikService.findKorisnikByEmail(kor.getEmail());
		
		ponuda.setOglas(oglasBaza);
		ponuda.setKorisnik(k);
		ponuda.setStatus("NISTA");
		Ponuda r =ponudaService.save(ponuda);
		Ponuda iz_baze = ponudaService.findById(ponuda.getId());
		
		korisnikService.findKorisnikByEmail(k.getEmail()).getPonude().add(iz_baze);			
		korisnikService.save(korisnikService.findKorisnikByEmail(k.getEmail()));
		oglasService.findById(oglasRoditelj.getId()).getPonude().add(iz_baze);
		
		return new ResponseEntity<>(r, HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping(value="/findClickedPonuda", method = RequestMethod.POST)
	public ResponseEntity<Ponuda>findSelected(@RequestBody String data, HttpServletRequest request){
		
		System.out.println(data);
		data = data.replaceAll("%22", "");
		System.out.println("NOVI DATA : "+data);
		data = data.replace("id=", "");
		System.out.println("NOVI DATA : "+data);
		
		Long id = Long.parseLong(data,10);
		
		
		
		List<Ponuda> ponude = ponudaService.findAll();
		Ponuda ret=null;
		for(Ponuda b: ponude){
			if(b.getId().compareTo(id)==0){
				ret = b;
				context.setAttribute("ponudaAktivna", ret);// samo postavimo koji je poslednji kliknut
			}
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getSelectedPonuda", method = RequestMethod.GET)
	public ResponseEntity<Ponuda>getSelectedRekvizit(HttpServletRequest request){
		
		Ponuda b = null;
		b = (Ponuda) context.getAttribute("ponudaAktivna");
		
		return new ResponseEntity<>(b, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/izmeni", method = RequestMethod.PUT)
	public Ponuda izmeni(@RequestBody Ponuda requestPonuda){
		
		
	
		//System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Ponuda iz_baze = ponudaService.findById(requestPonuda.getId());
		
		
		System.out.println("\nMENJAM ponudu: "+iz_baze.getId());
		ponudaService.findById(requestPonuda.getId()).setCena(requestPonuda.getCena());
		
		
		context.setAttribute("ponudaAktivna", ponudaService.findById(requestPonuda.getId()));
		ponudaService.save(ponudaService.findById(requestPonuda.getId()));
		return ponudaService.findById(requestPonuda.getId());
	
	}//
	
	@RequestMapping(value = "/prihvatiPonudu", method = RequestMethod.PUT)
	public Ponuda prihvatiPonudu(@RequestBody Ponuda requestPonuda,HttpServletRequest request){
		Oglas oglasRoditelj = null;
		oglasRoditelj = (Oglas) context.getAttribute("oglasPonuda");
		
		Oglas oglasBaza = oglasService.findById(oglasRoditelj.getId());
		
		Korisnik kor = (Korisnik)request.getSession().getAttribute("aktivanKorisnik");
		Korisnik k = korisnikService.findKorisnikByEmail(kor.getEmail());

		//System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Ponuda iz_baze = ponudaService.findById(requestPonuda.getId());
		
		
		System.out.println("\nPrihvatam ponudu: "+iz_baze.getId());
		ponudaService.findById(requestPonuda.getId()).setCena(requestPonuda.getCena());
		ponudaService.findById(requestPonuda.getId()).setStatus("ODOBRENA");
		
		for(Ponuda p : oglasBaza.getPonude()){
			if((p.getId()!= ponudaService.findById(requestPonuda.getId()).getId())){
				p.setStatus("ODBIJENA");
			}
			
		}
		oglasBaza.setProdat(true);
		context.setAttribute("oglasPonuda", oglasBaza);
		oglasService.save(oglasService.findById(oglasBaza.getId()));
		context.setAttribute("ponudaAktivna", ponudaService.findById(requestPonuda.getId()));
		ponudaService.save(ponudaService.findById(requestPonuda.getId()));
		
		System.out.println("\nasdadsa ***"+oglasBaza.getPonude().size());
		
		
		//slanje poruke nakon odobravanja ponude
		List<Korisnik> korisnici = korisnikService.findAll();
		
		List<Korisnik> odbijeniKorisnici = new ArrayList<Korisnik>();
		List<Korisnik> odobreniKorisnici = new ArrayList<Korisnik>();
		

		for(Korisnik kkk : korisnici){
			if(kkk.getTipKorisnika().equals("REGISTROVAN")){
				System.out.println("\n 1p");
				//ako korisnik ima ponudu na prodatom oglasu				
				for(Ponuda ponudeProdatog : oglasBaza.getPonude()){// ponude sa prodatog oglasa
					
					for(Ponuda ponudeKor : kkk.getPonude() ){
						
						if(ponudeProdatog.getId() == ponudeKor.getId()){//ako se nadju takve ponude  proveri status sa prodate i posalji poruku
							
							
							Poruka poruka1 = new Poruka();
							Poruka poruka2 = new Poruka();
							if(ponudeProdatog.getStatus().equals("ODBIJENA")){
							
								poruka1.setSadrzaj("Vasa ponuda("+ponudeProdatog.getCena()+") je ODBIJENA! Za oglas:"+ oglasBaza.getNaziv());							
								poruka1.setKorisnik(korisnikService.findKorisnikByEmail(kkk.getEmail()));
								
								porukaService.save(poruka1);
								kkk.getPoruka().add(poruka1);
								
								odbijeniKorisnici.add(kkk);
								//korisnikService.save(korisnikService.findKorisnikByEmail(kkk.getEmail()));
								
								
							}
							if(ponudeProdatog.getStatus().equals("ODOBRENA")){
								
								poruka2.setSadrzaj("Vasa ponuda("+ponudeProdatog.getCena()+") je ODOBRENA! Za oglas:"+ oglasBaza.getNaziv());							
								poruka2.setKorisnik(korisnikService.findKorisnikByEmail(kkk.getEmail()));							
								porukaService.save(poruka2);
								kkk.getPoruka().add(poruka2);								
								odobreniKorisnici.add(kkk);
							//	korisnikService.save(korisnikService.findKorisnikByEmail(kkk.getEmail()));
								
							}					
						}
					}
				}
				
			}
		}
		// treba da nadjes korisnika kome se poklama email i onda ga izmenis jer se u novom korisniku nalaze poruke 
		for(Korisnik izBazeKor : korisnici){
			if(izBazeKor.getTipKorisnika().equals("REGISTROVAN")){
				for( Korisnik ko : odobreniKorisnici ){
					if(ko.getEmail().equals(izBazeKor.getEmail())){
						korisnikService.save(korisnikService.findKorisnikByEmail(ko.getEmail()));
					}
				}
			}
		}
		for(Korisnik izBazeKor : korisnici){
			if(izBazeKor.getTipKorisnika().equals("REGISTROVAN")){
				for( Korisnik ko : odbijeniKorisnici ){
					if(ko.getEmail().equals(izBazeKor.getEmail())){
						korisnikService.save(korisnikService.findKorisnikByEmail(ko.getEmail()));
					}
				}
			}
		}
		
		for(Korisnik izBazeKor : korisnici){
			if(izBazeKor.getTipKorisnika().equals("REGISTROVAN")){
				for(Poruka pppp : izBazeKor.getPoruka()){
					System.out.println("\n PORUKE"+pppp.getSadrzaj());
				}
			}
		}
		
		return ponudaService.findById(requestPonuda.getId());
	
	}
	
}
