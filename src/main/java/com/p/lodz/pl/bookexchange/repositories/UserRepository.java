package com.p.lodz.pl.bookexchange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.p.lodz.pl.bookexchange.entities.UserData;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {

	public UserData findByUsername(String username);
	
	public UserData findByEmail(String email);

}
