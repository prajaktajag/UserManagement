package com.itwebsmart.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.itwebsmart.app.entities.User;

public interface UserService extends UserDetailsService {
	
	User saveUser(User user);
    User findByUsername(String username);
    Page<User> getAllUsers(Pageable pageable);

}
