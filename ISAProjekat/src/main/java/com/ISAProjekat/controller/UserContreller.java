package com.ISAProjekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ISAProjekat.model.User;
import com.ISAProjekat.service.UserService;

@RestController
@RequestMapping("/userController")
public class UserContreller {
	
	@Autowired
	private UserService userService ;

	@RequestMapping(value = "/getUsers/user", method = RequestMethod.GET) 
	@ResponseBody
	public List<User> getUsers(){
		return userService.findAllUsers();
		
	}

}
