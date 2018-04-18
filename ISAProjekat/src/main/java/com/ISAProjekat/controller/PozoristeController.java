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
import com.ISAProjekat.model.Pozoriste;
import com.ISAProjekat.model.Projekcija;
import com.ISAProjekat.model.Sala;
import com.ISAProjekat.service.PozoristeService;
import com.ISAProjekat.service.ProjekcijaService;
import com.ISAProjekat.service.SalaService;

@RestController
@RequestMapping("/pozoristeController")
public class PozoristeController {
	@Autowired
	private PozoristeService pozoristeService;
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private ProjekcijaService projekcijaService;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="/getPozorista", method = RequestMethod.GET)
	public ResponseEntity<List<Pozoriste>> getPozorista(){
		List<Pozoriste> pozorista = pozoristeService.findAll();
		List<Sala> sale = salaService.findAll();
		List<Projekcija> projekcije = projekcijaService.findAll();
		
		Pozoriste p1 = new Pozoriste("Pozoriste1","Stefana Milanovica BB, Novi Sad","Novi bioskop u gradu, 200 sala, besplatni prikazi prvih 300 godina.",0);
		Pozoriste p2 = new Pozoriste("Pozoriste2","Jovice Cubrica 142, Novi Sad","Novo pozoriste u gradu, 1500 sala, besplatni prikazi prvih 35 godina.",0);
		
		Pozoriste p3 = new Pozoriste("Pozoriste3","Aleksandra Lupica 33, Novi Sad","Novo pozoriste u gradu, 1500 sala, besplatni prikazi prvih 35 godina.",0);
		
		Pozoriste p4 = new Pozoriste("Pozoriste4","Jefimije Zivkovic 14, Novi Sad","Novo pozoriste u gradu, 1500 sala, besplatni prikazi prvih 35 godina.", 0);
		
		if(pozorista.isEmpty()){			
			
			pozoristeService.save(p1);
			pozoristeService.save(p2);
			pozoristeService.save(p3);
			pozoristeService.save(p4);
			
			pozorista = pozoristeService.findAll();
					
			System.out.println("DODATI BIOSKOPI U BAZU.");
			
			
			if(sale.isEmpty()){
				
				//Sala s1 = new Sala("Sala1",)
				//Sala s2 = new Sala("Sala 2", pozorista.get(0).getId());
				
				//salaService.save(s1);
				//salaService.save(s2);
				
				sale = salaService.findAll();
				
				System.out.println("DODATE SALE U BAZU.");
			
			
				if(projekcije.isEmpty()){
					
				/*	Projekcija pr1 = new Projekcija("Hamlet", "Tragedija", "Vilijam Sekspir", "3:00", 0, 0, "Ima nesto trulo u drzavi Danskoj.", 
							300.00, "Piter Stelers, Dzordz C. Scott", sale.get(0).getId(), sale.get(0).getNaziv());
					
					Projekcija pr2 = new Projekcija("Makbet", "Tragedija", "Vilijam Sekspir", "2:23", 0, 0, "Zivot je samo senka koja hoda, kukavni glumac sto na pozornici Sat-dva se puci i razbacuje...", 
							350.00, "Takashi Shimura", sale.get(1).getId(),sale.get(1).getNaziv());
					
					projekcijaService.save(pr1);
					projekcijaService.save(pr2);*/
				}
			}
		}
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
				context.setAttribute("pozoristeProfil", ret);
			}
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getSelectedPozoriste", method = RequestMethod.GET)
	public ResponseEntity<Pozoriste>getSelectedPozoriste(HttpServletRequest request){
		
		Pozoriste b = null;
		b = (Pozoriste) context.getAttribute("pozoristeProfil");
		System.out.println("SALJE NA PROFIL: "+b.getAdresa());
		
		return new ResponseEntity<>(b, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getSelectedPozoristeSale", method = RequestMethod.GET)
	public ResponseEntity<List<Sala>>getSelectedPozoristeSale(HttpServletRequest request){
		
		List<Sala> sale = salaService.findAll();
		Pozoriste b = (Pozoriste) context.getAttribute("pozoristeProfil");
		
		ArrayList<Sala> ret = new ArrayList<Sala>();
	
	//	for(Sala s: sale){
		//	if(s.getId_parent().compareTo(b.getId())==0){
		//		ret.add(s);
		//	}
		//}*/
		
		context.setAttribute("sale_poz", ret);
		return new ResponseEntity<>(ret, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getSelectedPozoristeProjekcije", method = RequestMethod.GET)
	public ResponseEntity<List<Projekcija>> getSelectedPozoristeProjekcije(HttpServletRequest request){
		
		List<Projekcija> projekcije = projekcijaService.findAll();
		//Pozoriste b = (Pozoriste) context.getAttribute("pozoristeProfil");
		
		ArrayList<Sala> sale = (ArrayList<Sala>) context.getAttribute("sale_poz");
		ArrayList<Projekcija> ret = new ArrayList<Projekcija>();	
		/*for(Projekcija p: projekcije){
			for(Sala s: sale){
				if(p.getSala().compareTo(s.getId())==0){
					ret.add(p);
				}
			}
		}
		*/
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/editPozoriste", method= RequestMethod.PUT)
	public ResponseEntity<Pozoriste> editPozoriste(@RequestBody Pozoriste requestPozoriste)
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
		context.setAttribute("pozoristeProfil", pozoristeService.findPozoristeById(requestPozoriste.getId()));
		
		
		
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
}
