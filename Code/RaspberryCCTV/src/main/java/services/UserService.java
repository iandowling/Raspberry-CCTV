package services;

import java.util.Collection;
import java.util.Optional;
import domain.User;

public interface UserService {
	
	Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);
    
    Optional<User> getUserByUsername(String username);

    Collection<User> getAllUsers();

    User create(CreateUserForm form);
}
