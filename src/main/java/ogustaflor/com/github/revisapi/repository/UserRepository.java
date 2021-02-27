package ogustaflor.com.github.revisapi.repository;

import ogustaflor.com.github.revisapi.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	
	boolean existsByEmail(String email);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmailOrUsername(String email, String username);
	
	boolean existsByEmailOrUsernameAndIdNot(String email, String username, Long id);
	
}
