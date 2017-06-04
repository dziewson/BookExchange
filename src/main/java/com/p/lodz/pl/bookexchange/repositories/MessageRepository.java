package com.p.lodz.pl.bookexchange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p.lodz.pl.bookexchange.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{
	
	public Message findBySenderId(Long senderId);
	
	public Message findByReciverId(Long reciverId);	


}
