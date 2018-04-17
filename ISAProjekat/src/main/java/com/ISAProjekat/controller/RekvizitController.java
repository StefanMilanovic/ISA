package com.ISAProjekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ISAProjekat.model.Rekvizit;
import com.ISAProjekat.service.RekvizitService;

@RestController
@RequestMapping(value = "/rekvizitController")
public class RekvizitController {

	@Autowired
	private RekvizitService rekvizitService;
	
	@RequestMapping(value="dodajRekvizit",method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Rekvizit> addRekvizit(@RequestBody Rekvizit rekvizit){
		Rekvizit r = rekvizitService.save(rekvizit);
		return new ResponseEntity<>(r, HttpStatus.OK);
	}	
	
	@RequestMapping(value="getRekviziti", method = RequestMethod.GET)
	public ResponseEntity<List<Rekvizit>> getRekviziti() {
		System.out.println("\nUzimam rekvizit ..");
		List<Rekvizit> rr = rekvizitService.findAll();
		return new ResponseEntity<>(rr, HttpStatus.OK);
	}
	
	@RequestMapping(value = "obrisi", method = RequestMethod.DELETE)
	public ResponseEntity<Rekvizit> delete(@RequestBody Rekvizit rekvizit,@PathVariable Long id) {
		System.out.println("************\nasdasd");
		long pom;
		//Rekvizit deleted = rekvizitService.delete(id);
		Rekvizit deleted = new Rekvizit();
		for(Rekvizit r : rekvizitService.findAll()){
			if(r.getId().equals(rekvizit.getId())){
				System.out.println("\n Nasao sam rekvizit!\n");
				pom = r.getId();
				deleted = rekvizitService.delete(r.getId());
			}
			
		}
		
	//	
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}
