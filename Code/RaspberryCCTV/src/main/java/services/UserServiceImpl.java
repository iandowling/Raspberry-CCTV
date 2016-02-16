package services;

import domain.User;
import domain.UserRepository;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/*
 * The service proxies to the UserRepository most of the time. 
 * Worth noticing is that in the create() method, the form is used to build a new User object. 
 * The hash is generated from the password using BCryptPasswordEncoder, 
 * which is supposed to generate better hashes than infamous MD5.
 */
@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	
	@Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRepository.findOneById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }
    
    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll(new Sort("username"));
    }

    @Override
    public User create(CreateUserForm form) {
        User user = new User();
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        return userRepository.save(user);
    }
}
