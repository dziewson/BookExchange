package com.p.lodz.pl.bookexchange.services;

import java.util.List;

import com.p.lodz.pl.bookexchange.entities.Role;

public interface RoleService {
	public Role findByName(String username);

	public List<Role> findAll();

	public Role findById(Long id);
}
