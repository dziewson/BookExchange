package com.p.lodz.pl.bookexchange.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p.lodz.pl.bookexchange.entities.Role;
import com.p.lodz.pl.bookexchange.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;

	public Role findByName(String userRole) {
		return roleRepository.findByuserRole(userRole);
	}

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	public Role findById(Long id) {
		return roleRepository.getOne(id);
	}

}
