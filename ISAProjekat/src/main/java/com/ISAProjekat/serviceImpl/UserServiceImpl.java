
package com.ISAProjekat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ISAProjekat.model.User;
import com.ISAProjekat.repository.UserRepository;
import com.ISAProjekat.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User getUser(String email) {
		Assert.notNull(email, "Email ne sme biti null");
		// TODO Auto-generated method stub
		return this.userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAllUsers() {
		return this.userRepository.findAll();
		
	}

}

