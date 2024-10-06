package com.itwebsmart.app.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itwebsmart.app.entities.Role;
import com.itwebsmart.app.entities.User;
import com.itwebsmart.app.repository.RoleRepository;
import com.itwebsmart.app.repository.UserRepository;

@Service
public class UserServiceIMPL implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceIMPL(UserRepository userRepository, 
                           RoleRepository roleRepository, 
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        // Assign roles based on the username
        Set<Role> roles = new HashSet<>();
        if (user.getUsername().equals("admin")) {
            roles.add(roleRepository.findByName("ROLE_ADMIN"));
        } else {
            roles.add(roleRepository.findByName("ROLE_USER"));
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Ensure this line is present
        return userRepository.save(user);
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username); // Fetch the user by username
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username); // Handle user not found
        }
        // Create and return UserDetails object
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user) // Convert roles to GrantedAuthority
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream()
                .map(role -> new org.springframework.security.core.authority.SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
