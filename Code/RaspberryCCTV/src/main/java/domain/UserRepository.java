package domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Only one non-default method findOneByEmail is added here. 
 * Notice that I want it to return a User wrapped in JDK8 Optional, 
 * which is somewhat a new feature of Spring, 
 * and makes handling null values easier.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 	 
	 Optional<User> findOneByUsername(String username);
	 Optional<User> findOneByEmail(String email);
	 Optional<User> findOneById(Long id);
}
