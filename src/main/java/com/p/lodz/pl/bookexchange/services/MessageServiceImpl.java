package com.p.lodz.pl.bookexchange.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p.lodz.pl.bookexchange.entities.Message;
import com.p.lodz.pl.bookexchange.repositories.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	UserService userService;

	@Override
	public Message findBySenderName(String username) {
		return messageRepository.findBySenderId(userService.findByName(username).getID());
	}

	@Override
	public Message findByReciverName(String username) {
		return messageRepository.findByReciverId(userService.findByName(username).getID());

	}

	@Override
	public Message findBySenderId(Long id) {
		return messageRepository.findBySenderId(id);
	}

	@Override
	public Message findByReciverId(Long id) {
		return messageRepository.findByReciverId(id);
	}
	
	@Override
	public void save(Message message) {
		messageRepository.save(message);
	}

	@Override
	public List<Message> findAll() {		
		return messageRepository.findAll();
	}
}
