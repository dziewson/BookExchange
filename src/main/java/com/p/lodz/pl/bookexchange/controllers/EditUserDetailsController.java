package com.p.lodz.pl.bookexchange.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.p.lodz.pl.bookexchange.entities.Role;
import com.p.lodz.pl.bookexchange.entities.UserData;
import com.p.lodz.pl.bookexchange.services.RoleService;
import com.p.lodz.pl.bookexchange.services.UserService;

@Controller
public class EditUserDetailsController {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	@RequestMapping(value = "/edituserdetails")
	public String editUser(@ModelAttribute("userToEditID") Long id, Model model) {
		model.addAttribute("user", userService.findById(id));
		model.addAttribute("allRoles", roleService.findAll());
		return "edituserdetails";
	}

	@RequestMapping(value = "/editSelectedUser", method = RequestMethod.POST)
	public String editSelectedUser(@ModelAttribute("user") UserData accountData, BindingResult result, Model model,
			@RequestParam List<Long> newRoles) {
		Set<Role> userRoles = new HashSet<>();
		for (Long id : newRoles) {
			userRoles.add(roleService.findById(id));
		}
		accountData.setRoles(userRoles);
		userService.update(accountData);
		return "redirect:/users";
	}

}
