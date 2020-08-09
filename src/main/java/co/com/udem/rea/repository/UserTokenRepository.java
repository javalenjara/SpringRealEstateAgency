package co.com.udem.rea.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.com.udem.rea.entity.UserToken;

public interface UserTokenRepository extends CrudRepository<UserToken, Long> {

	//@Query("SELECT u FROM UserToken u WHERE u.username = ?1")
	Optional<UserToken> findByToken(String token);
}
