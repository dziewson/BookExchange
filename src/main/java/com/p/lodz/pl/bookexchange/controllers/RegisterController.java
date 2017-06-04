
package com.p.lodz.pl.bookexchange.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.p.lodz.pl.bookexchange.entities.UserData;
import com.p.lodz.pl.bookexchange.services.SecurityService;
import com.p.lodz.pl.bookexchange.services.UserService;
import com.p.lodz.pl.bookexchange.utils.ImageUtils;
import com.p.lodz.pl.bookexchange.validators.UserValidator;

@Controller

@SessionAttributes("UserData")
public class RegisterController {

	@Autowired
	private UserService userService;
	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
		model.addAttribute("user", new UserData());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUserAccount(@ModelAttribute("user") UserData accountData, BindingResult result, Model model)
			throws IOException {
		userValidator.validate(accountData, result);
		accountData.setImage(ImageUtils.extractBytes("src" + File.separator + "main" + File.separator + "resources"
				+ File.separator + "static" + File.separator + "images" + File.separator + "user_placeholder.png"));
		if (result.hasErrors()) {
			return "register";
		}
		String password = accountData.getPassword();
		userService.create(accountData);
		securityService.autoLogin(accountData.getUsername(), password);
		return "index";
	}

}
