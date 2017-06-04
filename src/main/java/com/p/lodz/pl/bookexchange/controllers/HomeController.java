package com.p.lodz.pl.bookexchange.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.p.lodz.pl.bookexchange.entities.Book;
import com.p.lodz.pl.bookexchange.entities.Post;
import com.p.lodz.pl.bookexchange.entities.UserData;
import com.p.lodz.pl.bookexchange.services.BookService;
import com.p.lodz.pl.bookexchange.services.PostService;
import com.p.lodz.pl.bookexchange.utils.ImageUtils;

@Controller
public class HomeController {
	@Autowired
	BookService bookService;
	@Autowired
	private PostService postService;;

	@RequestMapping("/")
	String index(Model model, Pageable pageable) {
		UserData user = null;
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserData) {

			user = (UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}

		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("avatar", ImageUtils.getDecodedImage(user.getImage()));
			model.addAttribute("books", getUserBooks(user.getUsername()));
		} else {
			model.addAttribute("books", new ArrayList<Book>());
		}
		model.addAttribute("book", new Book());
		model.addAttribute("post", new Post());
		List<Post> posts = postService.findAll();
		 Collections.reverse(posts);
		model.addAttribute("allPosts", posts);
		 
		return "index";
	}

	@RequestMapping(value = "/home/page/{id}", method = RequestMethod.GET)
	public String historyDetail(Model model, @PathVariable long id, Pageable p) {
		/*
		 * Page<Post> page = postRepo.findBySyncJobIdOrderByLastUpdateDesc(id,
		 * p); model.addAttribute("page", page);
		 */
		return "index";
	}

	private List<Book> getUserBooks(String username) {
		List<Book> allBooks = bookService.findAll();
		List<Book> userBooks = new ArrayList<>();
		List<Book> temp = new ArrayList<>();

		for (Book book : allBooks) {
			if (book.getOwnerName().equals(username)) {
				temp.add(book);
			}
		}

		for (int i = temp.size() - 1; i >= 0; i--) {
			userBooks.add(temp.get(i));
			if (userBooks.size() == 11) {
				break;
			}
		}

		return userBooks;
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBook(@ModelAttribute("book") Book book, Model model) throws IOException {
		UserData owner = (UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		book.setOwnerId(owner.getID());
		book.setOwnerName(owner.getUsername());
		book.setImage(ImageUtils.extractBytes("src" + File.separator + "main" + File.separator + "resources"
				+ File.separator + "static" + File.separator + "images" + File.separator + "book.png"));
		bookService.save(book);
		return "redirect:/";
	}

	@GetMapping(value = "/home/{username}")
	public String showBooks(@PathVariable String username, RedirectAttributes redirect) {
		redirect.addFlashAttribute("userBooks", username);
		return "redirect:/userbooks";
	}

	@PostMapping(value = "/home/publishPost")
	public String publishPost(@ModelAttribute("post") Post post, Model model) {
		UserData user = (UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		post.setAuthorName(user.getUsername());
		postService.save(post);
		return "redirect:/";
	}
}
