package serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import model.User;
import repository.UserRepository;
import service.UserService;

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
	public Page<User> findAllUsers() {
		return this.userRepository.findAll(null);
		
	}

}
