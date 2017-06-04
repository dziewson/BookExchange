package com.p.lodz.pl.bookexchange.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.p.lodz.pl.bookexchange.entities.Book;
import com.p.lodz.pl.bookexchange.services.BookService;

@Controller
public class UserBooksController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/userbooks")
	public String Send(@ModelAttribute("userBooks") String userName, Model model, String error, String logout) {
		List<Book> books = bookService.findAll();	
		model.addAttribute("books", clearBookList(userName, books));
		return "userbooks";
	}

	private List<Book> clearBookList(String username, List<Book> books) {
		List<Book> newBooksList = new ArrayList<>();		
		for(Book book : books)  {
			if(book.getOwnerName().equals(username)) {
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
}
