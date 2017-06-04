package com.p.lodz.pl.bookexchange.controllers;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.p.lodz.pl.bookexchange.entities.Message;
import com.p.lodz.pl.bookexchange.entities.UserData;
import com.p.lodz.pl.bookexchange.services.MessageService;
import com.p.lodz.pl.bookexchange.services.UserService;
import com.p.lodz.pl.bookexchange.utils.MessageUtil;

@Controller
public class SendController {
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;


	@RequestMapping(value = "/send")
	public String Send(@ModelAttribute("messageTo") String reciverName, Model model, String error, String logout) {
		model.addAttribute("messageUtil", new MessageUtil());
		if (reciverName.isEmpty()) {
			model.addAttribute("reciverName", "");
		} else {
			model.addAttribute("reciverName", reciverName);
		}

		return "send";
	}

	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public String sendMessage(@ModelAttribute("messageUtil") MessageUtil messageUtil, Model model) {
		UserData sender = (UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserData reciver = userService.findByName(messageUtil.getTo());
		if (reciver == null) {
			return "redirect:/send";
		}
		Message message = new Message();
		message.setText(messageUtil.getMessage());
		message.setTitle(messageUtil.getTitle());
		message.setSenderId(sender.getID());
		message.setSenderName(sender.getUsername());
		message.setReciverName(reciver.getUsername());
		message.setReciverId(reciver.getID());
		message.setDate(getCurrentDate());
		messageService.save(message);
		return "redirect:/send";
	}

	private Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();

		return new Date(cal.getTimeInMillis());
	}
}
