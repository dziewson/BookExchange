package com.p.lodz.pl.bookexchange.services;

import java.util.List;

import com.p.lodz.pl.bookexchange.entities.Message;

public interface MessageService {
	public Message findBySenderName(String username);

	public Message findByReciverName(String username);
	
	public Message findBySenderId(Long id);
	
	public Message findByReciverId(Long id);

	public void save(Message message);
	
	public List<Message> findAll();
}
