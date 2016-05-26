package com.rcctv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rcctv.entities.User;

/*
 * creates the user repository methods which use the JpaRepository 
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

	User findByForgotPasswordCode(String forgotPasswordCode);

}
