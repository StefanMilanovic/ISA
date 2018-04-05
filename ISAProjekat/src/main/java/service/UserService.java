package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import model.User;

public interface UserService {

	User getUser(String email);
	Page<User> findAllUsers();
}
