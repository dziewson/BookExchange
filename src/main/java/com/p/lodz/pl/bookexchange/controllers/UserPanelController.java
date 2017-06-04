package com.p.lodz.pl.bookexchange.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.p.lodz.pl.bookexchange.dto.ProfileImageDTO;
import com.p.lodz.pl.bookexchange.entities.UserData;
import com.p.lodz.pl.bookexchange.services.UserService;
import com.p.lodz.pl.bookexchange.utils.ImageUtils;

@Controller
public class UserPanelController {

	@Autowired
	UserService userService;
	ProfileImageDTO image;

	@RequestMapping(value = "/userpanel")
	public String userPanel(Model model, ModelAndView view) {
		UserData user = (UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", user);

		model.addAttribute("avatar", ImageUtils.getDecodedImage(user.getImage()));

		return "userpanel";
	}

	@RequestMapping(value = "/changeUserImage", method = RequestMethod.POST)
	public String changeUserImage(@ModelAttribute("profileImage") ProfileImageDTO profileImage, Model model)
			throws IOException {
		UserData user = (UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user.setImage(ImageUtils.resizeImage(profileImage.getFile().getBytes()));
		userService.update(user);
		return "forward:/userpanel";
	}

	@RequestMapping(value = "/editCurrentUser", method = RequestMethod.POST)
	public String editCUrrentUser(@ModelAttribute("user") UserData accountData, Model model) throws IOException {

		UserData user = (UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		accountData.setRoles(user.getRoles());
		userService.update(accountData);
		return "index";
	}

	@RequestMapping(value = "/changePassword/{id}")
	public String redirectId(@PathVariable Long id, RedirectAttributes redirect) {
		redirect.addFlashAttribute("userToEditID", id);
		return "redirect:/changepassword";
	}

}
