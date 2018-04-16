package com.ISAProjekat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ISAProjekat.model.Oglas;
import com.ISAProjekat.model.DTO.OglasAdap;
import com.ISAProjekat.model.DTO.OglasDTO;
import com.ISAProjekat.model.DTO.OglasDTOAdap;
import com.ISAProjekat.service.OglasService;

@RestController
@RequestMapping(value = "/oglasController")
public class OglasController {

	
	
	@Autowired
	private OglasService oglasService;
	
	@Autowired
	private OglasAdap oglasAdap;
	
	@Autowired
	private OglasDTOAdap oglasDTOAdap;

	
/*
	@RequestMapping(value="/getOglasi", method = RequestMethod.GET)
	public ResponseEntity<List<Oglas>> getOglasi() {
		List<Oglas> oglasi = oglasService.findAll();
		
		System.out.println("\n Oglasi" +oglasi.get(0).getNaziv() +oglasi.get(0).getCena()+oglasi.get(0).getOpis()
				+oglasi.get(0).getSlika()+oglasi.get(0).getDatum());
		return new ResponseEntity<>(oglasi, HttpStatus.OK);
	}
	*/
	@RequestMapping(value="getOglasi", method = RequestMethod.GET)
	public ResponseEntity<List<OglasDTO>> getOglasi() {
		System.out.println("\nUzimam oglas");
		List<Oglas> oglasi = oglasService.findAll();
		return new ResponseEntity<>(oglasAdap.convert(oglasi), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="getOdobreniOglasi", method = RequestMethod.GET)
	public ResponseEntity<List<OglasDTO>> getOdobreniOglasi() {
		System.out.println("\nUzimam  odobreni oglas");
		List<Oglas> oglasi = oglasService.findAll();
		List<Oglas> odobreniOglasi  = new ArrayList<Oglas>();
		for(Oglas o : oglasi ){
			if(o.isOdobren()){
				odobreniOglasi.add(o);				
				
			}
			
		}
		return new ResponseEntity<>(oglasAdap.convert(odobreniOglasi), HttpStatus.OK);
	}
	
	@RequestMapping(value="dodajOglas",method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<OglasDTO> addOglas(@RequestBody OglasDTO oglasDTO){
		Oglas noviOglas = oglasService.save(oglasDTOAdap.convert(oglasDTO));
		System.out.println("\nDodajem oglas");
		
		return new ResponseEntity<>(oglasAdap.convert(noviOglas), HttpStatus.OK);
	}
}
