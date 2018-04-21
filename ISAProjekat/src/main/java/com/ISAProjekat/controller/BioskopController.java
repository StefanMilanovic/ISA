package com.ISAProjekat.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
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
import com.ISAProjekat.model.Dan;
import com.ISAProjekat.model.Karta;
import com.ISAProjekat.model.Korisnik;
import com.ISAProjekat.model.Mesec;
import com.ISAProjekat.model.Ocena;
import com.ISAProjekat.model.Projekcija;
import com.ISAProjekat.model.Sala;
import com.ISAProjekat.model.Sediste;
import com.ISAProjekat.service.BioskopService;
import com.ISAProjekat.service.DanService;
import com.ISAProjekat.service.KartaService;
import com.ISAProjekat.service.MesecService;
import com.ISAProjekat.service.OcenaService;
import com.ISAProjekat.service.ProjekcijaService;
import com.ISAProjekat.service.SalaService;
import com.ISAProjekat.service.SedisteService;

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
	
	@Autowired
	private OcenaService ocenaService;
	
	@Autowired
	private MesecService mesecService;
	
	@Autowired
	private DanService danService;
	
	@Autowired
	private SedisteService sedisteService;
	
	@Autowired
	private KartaService kartaService;
	
	@RequestMapping(value="/getBioskopi", method = RequestMethod.GET)
	public ResponseEntity<List<Bioskop>> getBioskopi(){
		List<Bioskop> bioskopi = bioskopService.findAll();
		List<Mesec> meseci = mesecService.findAll();
		
		for(Mesec m: meseci){
			if(m.getDani().isEmpty()){
				if(m.getBroj_meseca()== 2){
					for(int i=1; i<29; i++){
						Random r = new Random();
						int Low = 1;
						int High = 10;
						int posete_bio = r.nextInt(High-Low) + Low;
						int posete_poz = r.nextInt(High-Low) + Low;
						
						Dan d = new Dan(i, m, posete_poz,posete_bio);
						danService.save(d);
					}
				}
				else if(m.getBroj_meseca() == 1 || m.getBroj_meseca() == 3 || m.getBroj_meseca() == 5 || m.getBroj_meseca() == 7 || m.getBroj_meseca() == 8 
						|| m.getBroj_meseca() == 10 || m.getBroj_meseca() == 12) 
				{
					for(int i=1; i<32; i++){
						Random r = new Random();
						int Low = 1;
						int High = 10;
						int posete_bio = r.nextInt(High-Low) + Low;
						int posete_poz = r.nextInt(High-Low) + Low;
						
						Dan d = new Dan(i, m, posete_poz,posete_bio);
						danService.save(d);
					}
				}
				else{
					for(int i=1; i<31; i++){
						Random r = new Random();
						int Low = 1;
						int High = 10;
						int posete_bio = 0;
						int posete_poz =0;
						if(i<22){
							posete_bio = r.nextInt(High-Low) + Low;
							posete_poz = r.nextInt(High-Low) + Low;
						}												
						Dan d = new Dan(i, m, posete_poz,posete_bio);
						danService.save(d);
					}
				}		
			}
		}
		
		
		for(Bioskop b: bioskopi){
			if(b.getMeseci().isEmpty()){
				b.getMeseci().add(meseci.get(0));
				b.getMeseci().add(meseci.get(1));
				b.getMeseci().add(meseci.get(2));
				bioskopService.save(b);
			}
		}
		
		for(Bioskop b: bioskopi){
			for(Sala s: b.getSale()){
				if(s.getSedista().isEmpty()){
					for(int i=0; i<30; i++){
						if(i<10){
							Sediste sediste = new Sediste(true,false,s,null);
							sedisteService.save(sediste);
							s.getSedista().add(sediste);
						}
						else{
							Sediste sediste = new Sediste(false,false,s,null);
							sedisteService.save(sediste);
							s.getSedista().add(sediste);							
						}
					}
				}
			}
		}
		
		for(Bioskop b: bioskopi){
			for(Sala s: b.getSale()){
				for(Projekcija p: s.getProjekcije()){
					for(Sediste ss: s.getSedista()){
						if(ss.getKarte().isEmpty()){
							Karta k = new Karta(null,p,ss,10);
							kartaService.save(k);
							ss.getKarte().add(k);	
							sedisteService.save(ss);
						}						
					}
					
				}
				salaService.save(s);
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
				request.getSession().setAttribute("bioskopProfil", ret);
				//context.setAttribute("bioskopProfil", ret);
			}
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getLoggedUser", method = RequestMethod.GET)
	public Korisnik getLoggedUser(HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("aktivanKorisnik");
		return k;
	}
	
	
	@RequestMapping(value="/getSelectedBioskop", method = RequestMethod.GET)
	public ResponseEntity<Bioskop>getSelectedBioskop(HttpServletRequest request){
		
		Bioskop b = null;
		b = (Bioskop) request.getSession().getAttribute("bioskopProfil");
		Korisnik k = (Korisnik) request.getSession().getAttribute("aktivanKorisnik");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		LocalDateTime now = LocalDateTime.now();
		String s = dtf.format(now);
		
		String[] split = s.split("/");
		String mesec = split[1];
		String dan = split[2];
		
		int dann=Integer.parseInt(dan);
		int mesecc = Integer.parseInt(mesec);
		if(k!=null){
			for(Mesec m: bioskopService.findBioskopById(b.getId()).getMeseci()){
				if(m.getBroj_meseca()==mesecc){
					for(Dan d : m.getDani()){
						if(d.getBroj_dana()==dann){
							d.setBroj_poseta_bio(d.getBroj_poseta_bio()+1);
							danService.save(danService.findDanById(d.getId()));								
						}
					}
				}
				mesecService.save(mesecService.findMesecById(m.getId()));
			
			}				
			bioskopService.save(bioskopService.findBioskopById(b.getId()));
			request.getSession().setAttribute("bioskopProfil", bioskopService.findBioskopById(b.getId()));
		}
		return new ResponseEntity<>(bioskopService.findBioskopById(b.getId()), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getSelectedBioskopSale", method = RequestMethod.GET)
	public ResponseEntity<java.util.Set<Sala>> getSelectedBioskopSale(HttpServletRequest request){
		
		Bioskop b = (Bioskop) request.getSession().getAttribute("bioskopProfil");		
		List<Sala>sve_sale = salaService.findAll();
		bioskopService.findBioskopById(b.getId()).setSale(new HashSet<Sala>());
		
	
		for(Sala s: sve_sale){
			if(s.getBioskop().getId().compareTo(bioskopService.findBioskopById(b.getId()).getId())==0){
				bioskopService.findBioskopById(b.getId()).getSale().add(s);
			}
		}
		bioskopService.save(bioskopService.findBioskopById(b.getId()));
		
		System.out.println("IZLAZI IZ GET SALE");
		
		//context.setAttribute("bioskopProfil", bioskopService.findBioskopById(b.getId()));
		request.getSession().setAttribute("bioskopProfil", bioskopService.findBioskopById(b.getId()));
		request.getSession().setAttribute("aktivneSale", bioskopService.findBioskopById(b.getId()).getSale());
		
		return new ResponseEntity<>(bioskopService.findBioskopById(b.getId()).getSale(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getSedista", method = RequestMethod.GET)
	public HashMap<String, Set<Sediste>> getSedista(HttpServletRequest request){
		
		Bioskop b = (Bioskop) request.getSession().getAttribute("bioskopProfil");	
		Set<Sala>aktivne_sale = (Set<Sala>) request.getSession().getAttribute("aktivneSale");
		
		HashMap<String, Set<Sediste>> ret = new HashMap<String,Set<Sediste>>();
		
	
		for(Sala s: aktivne_sale){
			ret.put(s.getNaziv(), s.getSedista());
		}
		bioskopService.save(bioskopService.findBioskopById(b.getId()));
		
		System.out.println("IZLAZI IZ GET SALE");
		
		request.getSession().setAttribute("aktivnaSedista", ret);
		
		return ret;
		
	}
	
	@RequestMapping(value="/getSelectedBioskopProjekcije", method = RequestMethod.GET)
	public ResponseEntity<List<Projekcija>> getSelectedBioskopProjekcije(HttpServletRequest request){
		
		List<Projekcija> projekcije = projekcijaService.findAll();
		//Bioskop b = (Bioskop) context.getAttribute("bioskopProfil");
		Bioskop b = (Bioskop) request.getSession().getAttribute("bioskopProfil");  
		
		List<Sala> sale = salaService.findAll();
		for(Sala s: sale){
			salaService.findSalaById(s.getId()).setProjekcije(new HashSet<Projekcija>());
		}
		
		ArrayList<Projekcija> ret = new ArrayList<Projekcija>();	
		for(Projekcija p: projekcije){
			for(Sala s : b.getSale()){
				if (p.getSala().getId().compareTo(s.getId())==0){
					salaService.findSalaById(s.getId()).getProjekcije().add(p);
					salaService.save(salaService.findSalaById(s.getId()));
					ret.add(p);
				}
			}
		}
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/editBioskop", method= RequestMethod.PUT)
	public ResponseEntity<Bioskop> editBioskop(@RequestBody Bioskop requestBioskop, HttpServletRequest request)
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
		//context.setAttribute("bioskopProfil", bioskopService.findBioskopById(requestBioskop.getId()));
		request.getSession().setAttribute("bioskopProfil", bioskopService.findBioskopById(requestBioskop.getId()));
		
		
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
		List<Karta> karte = kartaService.findAll();
		for(Ocena o: ocene){
			if(o.getProjekcija()!=null && o.getProjekcija().getId().compareTo(id)==0){
				ocenaService.delete(o.getId());
			}
		}
		for(Karta k: karte){
			if(k.getProjekcija().getId().compareTo(id)==0){
				kartaService.delete(kartaService.findKartaById(k.getId()).getId());
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
		request.getSession().setAttribute("aktivnaProjekcija", ret);
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getSelectedProjekcijaProfil", method = RequestMethod.GET)
	public ResponseEntity<Projekcija>getSelectedProjekcija(HttpServletRequest request){
		
		Projekcija b = null;
		//b = (Projekcija) context.getAttribute("aktivnaProjekcija");
		b = (Projekcija) request.getSession().getAttribute("aktivnaProjekcija");
		
		return new ResponseEntity<>(b, HttpStatus.OK);
	}

	
	//Registracija bioskopa s
	@RequestMapping(value = "/registracijaBioskopa", method = RequestMethod.POST)
	public ResponseEntity<Bioskop> registracijaBioskopa(@RequestBody Bioskop requestBioskop){
		
		//System.out.println("\n Poslati podaci :"+ requestKorisnik.getEmail()+"->" +requestKorisnik.getSifra());
		Bioskop preuzetBioskop = new Bioskop(requestBioskop.getNaziv(),requestBioskop.getAdresa(),requestBioskop.getOpis(), 0, 0, 0);
		
		List<Bioskop> lk = bioskopService.findAll() ;		
		//ako je baza prazna samo ga dodaj bez provere 
		if(lk.isEmpty()){
			if(!preuzetBioskop.getNaziv().isEmpty() && !preuzetBioskop.getOpis().isEmpty() && !preuzetBioskop.getAdresa().isEmpty()) 	
				
			{
				System.out.println("\nProsao 1");	
				preuzetBioskop.setSale(new HashSet<Sala>());
				
				//MARKOVO CUDO OD KODA- prilagodjavanje 111********************************
				
				bioskopService.save(preuzetBioskop);
				Mesec februar = new Mesec( "Februar",  2018, 2,  preuzetBioskop, null);
				Mesec mart = new Mesec( "Mart",  2018, 3,  preuzetBioskop, null);
				Mesec april = new Mesec( "April",  2018, 4 ,  preuzetBioskop, null);
				mesecService.save(februar);
				mesecService.save(mart);
				mesecService.save(april);
				List<Dan> daniFebruar= new ArrayList<Dan>();
				List<Dan> daniMart= new ArrayList<Dan>();
				List<Dan> daniApril= new ArrayList<Dan>();
				
				
				List<Mesec> meseci = new ArrayList<Mesec>();
				meseci.add(februar);
				meseci.add(mart);
				meseci.add(april);
				for(Mesec m: meseci){
					if(m.getDani().isEmpty()){
						if(m.getBroj_meseca()== 2){
							System.out.println("\n*******punim februar");
							for(int i=1; i<29; i++){
								Random r = new Random();
								int Low = 1;
								int High = 10;
								int posete_bio = r.nextInt(High-Low) + Low;
								int posete_poz = r.nextInt(High-Low) + Low;
								
								Dan d = new Dan(i, m, posete_poz,posete_bio);
								daniFebruar.add(d);
								//danService.save(d);
							}
						}
						else if(m.getBroj_meseca() == 1 || m.getBroj_meseca() == 3 || m.getBroj_meseca() == 5 || m.getBroj_meseca() == 7 || m.getBroj_meseca() == 8 
								|| m.getBroj_meseca() == 10 || m.getBroj_meseca() == 12) 
						{
							System.out.println("\n*******punim mart");
							for(int i=1; i<32; i++){
								Random r = new Random();
								int Low = 1;
								int High = 10;
								int posete_bio = r.nextInt(High-Low) + Low;
								int posete_poz = r.nextInt(High-Low) + Low;
								
								Dan d = new Dan(i, m, posete_poz,posete_bio);
								daniMart.add(d);
								//danService.save(d);
							}
						}
						else{
							System.out.println("\n*******punim april");
							for(int i=1; i<31; i++){
								Random r = new Random();
								int Low = 1;
								int High = 10;
								int posete_bio = 0;
								int posete_poz =0;
								if(i<22){
									posete_bio = r.nextInt(High-Low) + Low;
									posete_poz = r.nextInt(High-Low) + Low;
								}												
								Dan d = new Dan(i, m, posete_poz,posete_bio);
								daniApril.add(d);
								//danService.save(d);
							}
						}		
					}
				}
				
				//upisujemo u bazu dane koji su prethodno formirani
				System.out.println("\nOvde sam");
				System.out.println("\nOvde sam   " +daniFebruar.size());
				for(Dan dd : daniFebruar){
					System.out.println("\nOvde sam   " +dd.getBroj_dana());
					danService.save(dd);
				}
				for(Dan dd : daniMart){
					danService.save(dd);
				}
				for(Dan dd : daniApril){
					danService.save(dd);
				}
				
					if(preuzetBioskop.getMeseci().isEmpty()){
						preuzetBioskop.getMeseci().add(meseci.get(0));
						preuzetBioskop.getMeseci().add(meseci.get(1));
						preuzetBioskop.getMeseci().add(meseci.get(2));
						bioskopService.save(bioskopService.findBioskopById(preuzetBioskop.getId()));
					}
				
				
				
				
				
		//KRAJ CEGA GOD  222***************************************
				bioskopService.save(bioskopService.findBioskopById(preuzetBioskop.getId()));
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
				
				//MARKOVO CUDO OD KODA   222********************************
				bioskopService.save(preuzetBioskop);
				Mesec februar = new Mesec( "Februar",  2018, 2,  preuzetBioskop, null);
				Mesec mart = new Mesec( "Mart",  2018, 3,  preuzetBioskop, null);
				Mesec april = new Mesec( "April",  2018, 4 ,  preuzetBioskop, null);
				mesecService.save(februar);
				mesecService.save(mart);
				mesecService.save(april);
				List<Dan> daniFebruar= new ArrayList<Dan>();
				List<Dan> daniMart= new ArrayList<Dan>();
				List<Dan> daniApril= new ArrayList<Dan>();
				
				
				List<Mesec> meseci = new ArrayList<Mesec>();
				meseci.add(februar);
				meseci.add(mart);
				meseci.add(april);
				for(Mesec m: meseci){
					if(m.getDani().isEmpty()){
						if(m.getBroj_meseca()== 2){
							System.out.println("\n*******punim februar");
							for(int i=1; i<29; i++){
								Random r = new Random();
								int Low = 1;
								int High = 10;
								int posete_bio = r.nextInt(High-Low) + Low;
								int posete_poz = r.nextInt(High-Low) + Low;
								
								Dan d = new Dan(i, m, posete_poz,posete_bio);
								daniFebruar.add(d);
								//danService.save(d);
							}
						}
						else if(m.getBroj_meseca() == 1 || m.getBroj_meseca() == 3 || m.getBroj_meseca() == 5 || m.getBroj_meseca() == 7 || m.getBroj_meseca() == 8 
								|| m.getBroj_meseca() == 10 || m.getBroj_meseca() == 12) 
						{
							System.out.println("\n*******punim mart");
							for(int i=1; i<32; i++){
								Random r = new Random();
								int Low = 1;
								int High = 10;
								int posete_bio = r.nextInt(High-Low) + Low;
								int posete_poz = r.nextInt(High-Low) + Low;
								
								Dan d = new Dan(i, m, posete_poz,posete_bio);
								daniMart.add(d);
								//danService.save(d);
							}
						}
						else{
							System.out.println("\n*******punim april");
							for(int i=1; i<31; i++){
								Random r = new Random();
								int Low = 1;
								int High = 10;
								int posete_bio = 0;
								int posete_poz =0;
								if(i<22){
									posete_bio = r.nextInt(High-Low) + Low;
									posete_poz = r.nextInt(High-Low) + Low;
								}												
								Dan d = new Dan(i, m, posete_poz,posete_bio);
								daniApril.add(d);
								//danService.save(d);
							}
						}		
					}
				}
				
				//upisujemo u bazu dane koji su prethodno formirani
				System.out.println("\nOvde sam");
				System.out.println("\nOvde sam   " +daniFebruar.size());
				for(Dan dd : daniFebruar){
					System.out.println("\nOvde sam   " +dd.getBroj_dana());
					danService.save(dd);
				}
				for(Dan dd : daniMart){
					danService.save(dd);
				}
				for(Dan dd : daniApril){
					danService.save(dd);
				}
				
					if(preuzetBioskop.getMeseci().isEmpty()){
						preuzetBioskop.getMeseci().add(meseci.get(0));
						preuzetBioskop.getMeseci().add(meseci.get(1));
						preuzetBioskop.getMeseci().add(meseci.get(2));
						bioskopService.save(bioskopService.findBioskopById(preuzetBioskop.getId()));
					}
				
				
				
				
				
		//KRAJ CEGA GOD  222***************************************
				bioskopService.save(bioskopService.findBioskopById(preuzetBioskop.getId()));
//OVDE UBACI MENJANJE NOVOG BIOSKOPA
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
			preuzetSala.setVip_enabled(true);
			preuzetSala.setBioskop(bRoditelj);
			salaService.save(preuzetSala);
			bioskopService.findBioskopById(b.getId()).getSale().add(preuzetSala);
			bioskopService.save(bioskopService.findBioskopById(b.getId()));
			//markov kod 111
			
			List<Sala> saleZaCuvanje = new ArrayList<Sala>();
			
				if(preuzetSala.getSedista().isEmpty()){
					for(int i=0; i<30; i++){
						if(i<10){
							Sediste sediste = new Sediste(true,false,preuzetSala,null);
							sedisteService.save(sediste);
							preuzetSala.getSedista().add(sediste);
							//saleZaCuvanje.add(e)
						}
						else{
							Sediste sediste = new Sediste(false,false,preuzetSala,null);
							sedisteService.save(sediste);
							preuzetSala.getSedista().add(sediste);							
						}
					}
				}
			
			salaService.save(salaService.findSalaById(preuzetSala.getId()));	
//markov kod popravka kraj 
			
			
			
		
			
			return new ResponseEntity<Sala>(preuzetSala, HttpStatus.OK);
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
		request.getSession().setAttribute("editovanaProjekcija", projekcijaService.findProjekcijaById(id));
		return new ResponseEntity <>(projekcijaService.findProjekcijaById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getSelektovanuProjekciju", method= RequestMethod.GET)
	public ResponseEntity<Projekcija> getEditovanuProjekciju(HttpServletRequest request){						
		//Projekcija p = (Projekcija) context.getAttribute("editovanaProjekcija");			
		Projekcija p = (Projekcija) request.getSession().getAttribute("editovanaProjekcija");
		return new ResponseEntity <>(p, HttpStatus.OK);
	}
	
	@RequestMapping(value="/oceniBioskop", method= RequestMethod.PUT)
	public Bioskop oceniBioskop(@RequestBody String data, HttpServletRequest request)
	{								
		System.out.println(data);
		data = data.replaceAll("%22", "");
		System.out.println("NOVI DATA : "+data);
		data = data.replace("id=", "");
		System.out.println("NOVI DATA : "+data);
		
		boolean glasao = false;
		int vrednost = Integer.parseInt(data);
		//Bioskop b = (Bioskop) context.getAttribute("bioskopProfil");
		Bioskop b = (Bioskop) request.getSession().getAttribute("bioskopProfil");
		Korisnik k = (Korisnik)request.getSession().getAttribute("aktivanKorisnik");
		
		if(b.getOcene()!=null){
			for(Ocena o : bioskopService.findBioskopById(b.getId()).getOcene()){
				if(o.getKorisnik().getId().compareTo(k.getId())==0){
					glasao = true;		
					o.setVrednost(vrednost);
					bioskopService.save(bioskopService.findBioskopById(b.getId()));
				}
			}
		}
		
		if(glasao){
			System.out.println("Korisnik je vec ocenio, menja se stara vrednost");
			
			int ukupan_zbir =0 ;
			double prosecna_ocena;
			
			for(Ocena o : bioskopService.findBioskopById(b.getId()).getOcene()){			
				ukupan_zbir += o.getVrednost();
			}
			
			prosecna_ocena = (double)ukupan_zbir/bioskopService.findBioskopById(b.getId()).getBroj_glasova();
			
			bioskopService.findBioskopById(b.getId()).setProsecna_ocena(prosecna_ocena);
			bioskopService.save(bioskopService.findBioskopById(b.getId()));
			//context.setAttribute("bioskopProfil", bioskopService.findBioskopById(b.getId()));
			request.getSession().setAttribute("bioskopProfil", bioskopService.findBioskopById(b.getId()));
			return bioskopService.findBioskopById(b.getId());
		}
		
		
		Ocena nova_ocena = new Ocena(k, bioskopService.findBioskopById(b.getId()), null, null, vrednost);
		ocenaService.save(nova_ocena);
		
		bioskopService.findBioskopById(b.getId()).getOcene().add(nova_ocena);
		
		bioskopService.findBioskopById(b.getId()).setBroj_glasova(bioskopService.findBioskopById(b.getId()).getBroj_glasova()+1);
		
		int ukupan_zbir =0 ;
		double prosecna_ocena;
		
		for(Ocena o : bioskopService.findBioskopById(b.getId()).getOcene()){			
			ukupan_zbir += o.getVrednost();
		}
		System.out.println("UKUPAN ZBIR JE: "+ukupan_zbir);
		
		prosecna_ocena = (double)ukupan_zbir/bioskopService.findBioskopById(b.getId()).getBroj_glasova();
		bioskopService.findBioskopById(b.getId()).setProsecna_ocena(prosecna_ocena);
		bioskopService.save(bioskopService.findBioskopById(b.getId()));
		
		//context.setAttribute("bioskopProfil", bioskopService.findBioskopById(b.getId()));
		request.getSession().setAttribute("bioskopProfil", bioskopService.findBioskopById(b.getId()));
		
		return bioskopService.findBioskopById(b.getId());
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
	
	@RequestMapping(value="/getMesece", method = RequestMethod.GET)
	public HashMap<String,ArrayList<Dan>> getMesece(HttpServletRequest request){
		Bioskop b = (Bioskop) request.getSession().getAttribute("bioskopProfil");
		System.out.println("BROJ MESECI: "+b.getMeseci().size());
		ArrayList<Dan> dani = new ArrayList<Dan>();
		ArrayList<Mesec> meseci = new ArrayList<Mesec>();
		
		for(Mesec m: b.getMeseci()){
			meseci.add(m);
		}
		
		Collections.sort(meseci, new Comparator<Mesec>(){

			@Override
			public int compare(Mesec arg0, Mesec arg1) {				
				return Integer.compare(arg0.getBroj_meseca(), arg1.getBroj_meseca());
			}
			
		});
		
		HashMap<String,ArrayList<Dan>> podaci = new HashMap<String,ArrayList<Dan>>();
		
		
		
		for(Mesec m: meseci){
			dani = new ArrayList<Dan>();
			for(Dan d: m.getDani()){
				dani.add(d);
			}
			
			Collections.sort(dani, new Comparator<Dan>(){

				@Override
				public int compare(Dan arg0, Dan arg1) {				
					return Integer.compare(arg0.getBroj_dana(), arg1.getBroj_dana());
				}
				
			});
		
			podaci.put(m.getNaziv(), dani);
			
		}
		
		return podaci;
	}
	
	@RequestMapping(value="/editSala", method= RequestMethod.PUT)
	public Sala editSala(@RequestBody Sala data, HttpServletRequest request)
	{				
		Set<Sala> aktivne = (Set<Sala>) request.getSession().getAttribute("aktivneSale");
		for(Sala s : aktivne){
			if(s.getNaziv().equals(data.getNaziv())){
				if(s.isVip_enabled()){
					salaService.findSalaById(s.getId()).setVip_enabled(false);
					salaService.save(salaService.findSalaById(s.getId()));
					s.setVip_enabled(false);
				}
				else{
					salaService.findSalaById(s.getId()).setVip_enabled(true);
					salaService.save(salaService.findSalaById(s.getId()));
					s.setVip_enabled(true);
				}				
				
			}
		}
		request.getSession().setAttribute("aktivneSale", aktivne);
		
		return data;
	}
	
	@RequestMapping(value="/nadjiKarte", method= RequestMethod.GET)
	public List<Karta> findKarte(HttpServletRequest request)
	{				
		return kartaService.findAll();
	}
	
	@RequestMapping(value="/rezervisiKartu", method= RequestMethod.PUT)
	public Karta rezKartu(@RequestBody String data, HttpServletRequest request)
	{				
		System.out.println(data);
		data = data.replaceAll("%22", "");
		System.out.println("NOVI DATA : "+data);
		data = data.replace("id=", "");
		System.out.println("NOVI DATA : "+data);
		
		
		
		Long id = Long.parseLong(data,10);
		Karta iz_baze = kartaService.findKartaById(id);
		
		Bioskop b = (Bioskop) request.getSession().getAttribute("bioskopProfil");
		b.setUkupan_prihod(b.getUkupan_prihod()+iz_baze.getProjekcija().getCena());
		
		Korisnik k = (Korisnik)request.getSession().getAttribute("aktivanKorisnik");
		if(iz_baze.getKorisnik()==null){
			iz_baze.setKorisnik(k);
			kartaService.save(kartaService.findKartaById(iz_baze.getId()));
		}
		
		bioskopService.save(bioskopService.findBioskopById(b.getId()));
		
		
		return iz_baze;
	}
	
}
