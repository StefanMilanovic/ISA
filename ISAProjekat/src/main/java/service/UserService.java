package service;

import java.util.List;

import model.User;

public interface UserService {

	User getUser(String email);
	List<User> findAllUsers();
}

