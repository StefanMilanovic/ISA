package repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import model.User;

public interface UserRepository extends Repository<User, Long> {
//broj rezultata long Npr. ako se prosledi objekat: new PageRequest(0, 10)
	
	Page<User> findAll(Pageable pageable);
	
	
	//select c from User c where c.email = ?1
	User findByEmail(String email);


	
}
