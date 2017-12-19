package com.p.lodz.pl.bookexchange.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.p.lodz.pl.bookexchange.entities.Book;
import com.p.lodz.pl.bookexchange.entities.Intrests;
import com.p.lodz.pl.bookexchange.entities.UserData;
import com.p.lodz.pl.bookexchange.services.BookService;
import com.p.lodz.pl.bookexchange.services.IntrestsService;

@Controller
public class UserBooksController {

	@Autowired
	private BookService bookService;
	@Autowired
	private IntrestsService intrService;
	private String redirectedUser ;

	@RequestMapping(value = "/userbooks")
	public String Send(@ModelAttribute("userBooks") String userName, Model model, String error, String logout) {
		List<Book> books = bookService.findAll();
		UserData user = (UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("isSameUser", !user.getUsername().equals(redirectedUser));
		redirectedUser = userName;
		model.addAttribute("books", clearBookList(userName, books));
		return "userbooks";
	}

	private List<Book> clearBookList(String username, List<Book> books) {
		List<Book> newBooksList = new ArrayList<>();
		for (Book book : books) {
			if (book.getOwnerName().equals(username)) {
				newBooksList.add(book);
			}
		}
		return newBooksList;
	}

	@GetMapping(value = "/userbooks/{username}")
	public String sendMail(@PathVariable String username, RedirectAttributes redirect) {
		redirect.addFlashAttribute("messageTo", username);
		return "redirect:/send";
	}

	@GetMapping(value = "/userbooks/intrestIn/{catId}")
	public String showIntrests(@PathVariable String catId) {
		UserData user = (UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Intrests intr = new Intrests();
		intr.setCategoryId(Long.parseLong(catId.split(",")[0]));
		intr.setAuthorValue(countStringAsciValue(catId.split(",")[1], catId.split(",")[2]));
		intr.setUserId(user.getID());
		intrService.save(intr);	
		return "redirect:/showBooks/"+redirectedUser;
	}
	
	private  int countStringAsciValue(String...strings ) {
		int value = 0;
		
		for(String str : strings) {
			for(char c : str.toCharArray()) {
				value += (int) c;
			}
		}
		
		return value;
	}
}
