package com.p.lodz.pl.bookexchange.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.p.lodz.pl.bookexchange.entities.UserData;
import com.p.lodz.pl.bookexchange.exceptions.AdminRemoveException;
import com.p.lodz.pl.bookexchange.repositories.UserRepository;
import com.p.lodz.pl.bookexchange.services.UserService;

@Controller
public class UsersController {

	private static final String USERS = "users";
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepo;

	@RequestMapping(value = "/users")
	public String showUsers(Model model) {
		model.addAttribute(USERS, userService.findAll());
		return USERS;
	}

	@PostMapping(value = "/removeUser/{id}")
	public String removeUser(@PathVariable Long id) throws AdminRemoveException {
		UserData userToDelete = userService.findById(id);

		if (!userToDelete.isAdministrator()) {
			userRepo.deleteById(id);
		} else {
			throw new AdminRemoveException();
		}

		return "redirect:/users";
	}

	@RequestMapping(value = "/editUser/{id}")
	public String redirectId(@PathVariable Long id, RedirectAttributes redirect) {
		redirect.addFlashAttribute("userToEditID", id);
		return "redirect:/edituserdetails";
	}

	@GetMapping(value = "/privateMessage/{username}")
	public String sendPrivateMessage(@PathVariable String username, RedirectAttributes redirect) {		
		redirect.addFlashAttribute("messageTo", username);
		return "redirect:/send";
	}
	
	@GetMapping(value = "/showBooks/{username}")
	public String showBooks(@PathVariable String username, RedirectAttributes redirect) {		
		redirect.addFlashAttribute("userBooks", username);
		return "redirect:/userbooks";
	}
}
