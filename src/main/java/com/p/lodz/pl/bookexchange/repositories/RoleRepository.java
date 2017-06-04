package com.p.lodz.pl.bookexchange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p.lodz.pl.bookexchange.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	public Role findByuserRole(String userRole);
	
	
}