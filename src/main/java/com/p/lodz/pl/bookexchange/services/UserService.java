package com.p.lodz.pl.bookexchange.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.p.lodz.pl.bookexchange.entities.UserData;

public interface UserService extends UserDetailsService {
	public UserData findByName(String username);
	public UserData findByEmail(String email);
	public List<UserData> findAll();
	public void create(UserData user);
	public UserData save(UserData user);
	public UserData findById(Long id);
	public UserData update(UserData user);
}
