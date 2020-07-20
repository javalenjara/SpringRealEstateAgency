package co.com.udem.rea.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.com.udem.rea.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

}