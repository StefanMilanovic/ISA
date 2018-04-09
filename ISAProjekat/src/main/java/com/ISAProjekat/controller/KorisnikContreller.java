package com.ISAProjekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ISAProjekat.model.Korisnik;

import com.ISAProjekat.service.KorisnikService;

@RestController
@RequestMapping("/userController")
public class KorisnikContreller {
	
	@Autowired
	private KorisnikService userService ;

	@RequestMapping(value = "/getUsers/user", method = RequestMethod.GET) 
	@ResponseBody
	public List<Korisnik> getUsers(){
		return userService.findAllKorisnik();
		
	}

}
