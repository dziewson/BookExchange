package com.p.lodz.pl.bookexchange.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.p.lodz.pl.bookexchange.entities.Message;
import com.p.lodz.pl.bookexchange.entities.UserData;
import com.p.lodz.pl.bookexchange.services.MessageService;

@Controller
public class SentController {
	@Autowired
	private MessageService messageService;


	@RequestMapping("/sent")
	public String recived(Model model) {
		UserData currentUser = (UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Message> messages = messageService.findAll();
		clearMessageList(messages, currentUser.getID());
		model.addAttribute("messages", messages);
		return "sent";
	}

	private void clearMessageList(List<Message> messages, Long id) {
		List<Message> messageToRemove = new ArrayList<>();
		for (Message message : messages) {
			if (message.getSenderId() != id) {
				messageToRemove.add(message);
			}
		}
		messages.removeAll(messageToRemove);
	}

}
