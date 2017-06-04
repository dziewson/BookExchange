package com.p.lodz.pl.bookexchange.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.p.lodz.pl.bookexchange.entities.UserData;
import com.p.lodz.pl.bookexchange.services.UserService;

@Controller
public class ChangePasswordController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/changepassword")
	public String userPanel(@ModelAttribute("userToEditID") Long id, Model model) {
		model.addAttribute("user", userService.findById(id));
		return "changepassword";
	}

	@RequestMapping(value = "/changePasswordForm")
	public String changeUserPassword(@ModelAttribute("user") UserData user, BindingResult result) throws Exception {
		UserData currUser = (UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user.getPassword().equals(user.getMatchingPassword())) {
			user.setRoles(currUser.getRoles());
			userService.save(user);
		} else {
			throw new Exception();
		}

		return "redirect:/userpanel";
	}

}
