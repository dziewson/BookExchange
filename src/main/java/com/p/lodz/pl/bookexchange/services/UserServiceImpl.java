package com.p.lodz.pl.bookexchange.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.p.lodz.pl.bookexchange.entities.Role;
import com.p.lodz.pl.bookexchange.entities.Roles;
import com.p.lodz.pl.bookexchange.entities.UserData;
import com.p.lodz.pl.bookexchange.repositories.RoleRepository;
import com.p.lodz.pl.bookexchange.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	final static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserData findByName(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<UserData> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void create(UserData user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setMatchingPassword(bCryptPasswordEncoder.encode(user.getMatchingPassword()));
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findByuserRole(Roles.USER.toString()));
		user.setRoles(roles);
		userRepository.save(user);
	}

	public UserData update(UserData user) {	
		return userRepository.save(user);
	}

	@Override
	public UserData save(UserData user) {

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setMatchingPassword(bCryptPasswordEncoder.encode(user.getMatchingPassword()));

		if (user.getRoles() == null) {
			Set<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByuserRole(Roles.USER.toString()));
			user.setRoles(roles);
		}

		return userRepository.save(user);
	}

	@Override
	public UserData findById(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public UserData findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

}
