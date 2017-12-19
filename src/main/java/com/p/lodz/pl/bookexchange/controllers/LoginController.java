package com.p.lodz.pl.bookexchange.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller

@SessionAttributes("UserData")
public class LoginController {


	@RequestMapping(value = "/login")
	public String login(Model model, String error, String logout) {
		
		return "login";
	}
}
