package com.ISAProjekat.service;

import java.util.List;

import com.ISAProjekat.model.User;

public interface UserService {

	User getUser(String email);
	List<User> findAllUsers();
}

