package com.ISAProjekat.controller;

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

import com.ISAProjekat.model.FanZona;
import com.ISAProjekat.model.Rekvizit;
import com.ISAProjekat.service.FanZonaService;

@RestController
@RequestMapping(value = "/fanZonaController")
public class FanZonaController {

	
	@Autowired
	private FanZonaService fanZonaService;

	@Autowired
	ServletContext context;
	
	

	@RequestMapping(value="/getSelectedFanZona", method = RequestMethod.GET)
	public ResponseEntity<FanZona>getSelectedFanZona(HttpServletRequest request){
		
		FanZona b = null;
		b = (FanZona) context.getAttribute("fanZonaAktivna");
	
		return new ResponseEntity<>(b, HttpStatus.OK);
	}
	
	@RequestMapping(value="/findClickedFanZona", method = RequestMethod.POST)
	public ResponseEntity<FanZona>findSelectedFanZona(@RequestBody String data, HttpServletRequest request){
		
		System.out.println(data);
		data = data.replaceAll("%22", "");
		System.out.println("NOVI DATA : "+data);
		data = data.replace("id=", "");
		System.out.println("NOVI DATA : "+data);
		
		Long id = Long.parseLong(data,10);
		
		
		
		List<FanZona> fanZone = fanZonaService.findAll();
		FanZona ret=null;
		for(FanZona b: fanZone){
			if(b.getId().compareTo(id)==0){
				ret = b;
				context.setAttribute("fanZonaAktivna", ret);
			}
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@RequestMapping(value="getFanZona", method = RequestMethod.GET)
	public ResponseEntity<List<FanZona>> getFanZona() {
		System.out.println("\nUzimam rekvizit ..");
		List<FanZona> rr = fanZonaService.findAll();
		return new ResponseEntity<>(rr, HttpStatus.OK);
	}
	
}
