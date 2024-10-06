package com.itwebsmart.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwebsmart.app.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	 Role findByName(String name);

}
