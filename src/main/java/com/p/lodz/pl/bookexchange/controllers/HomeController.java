package com.p.lodz.pl.bookexchange.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;import org.neuroph.core.learning.LearningRule;
import org.neuroph.nnet.Perceptron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.p.lodz.pl.bookexchange.entities.Author;
import com.p.lodz.pl.bookexchange.entities.Book;
import com.p.lodz.pl.bookexchange.entities.Category;
import com.p.lodz.pl.bookexchange.entities.Intrests;
import com.p.lodz.pl.bookexchange.entities.Post;
import com.p.lodz.pl.bookexchange.entities.UserData;
import com.p.lodz.pl.bookexchange.pagination.PageWrapper;
import com.p.lodz.pl.bookexchange.services.AuthorService;
import com.p.lodz.pl.bookexchange.services.BookService;
import com.p.lodz.pl.bookexchange.services.CategoryService;
import com.p.lodz.pl.bookexchange.services.IntrestsService;
import com.p.lodz.pl.bookexchange.services.PostService;
import com.p.lodz.pl.bookexchange.services.UserService;
import com.p.lodz.pl.bookexchange.utils.GetLocation;
import com.p.lodz.pl.bookexchange.utils.ImageUtils;
import com.p.lodz.pl.bookexchange.utils.Location;

@Controller
public class HomeController {
	private static final String WRITTEN_BY = "written by ";
	@Autowired
	BookService bookService;
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService catService;
	@Autowired
	private IntrestsService intrestService;
	@Autowired
	private AuthorService authorService;
	private Location currentLocation;
	private String catName;

	@RequestMapping("/")
	String index(Model model, Pageable pageable) {
		UserData user = null;
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserData) {

			user = (UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			setUserGeoLocation(user);

		}

		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("avatar", ImageUtils.getDecodedImage(user.getImage()));
			model.addAttribute("userBooks", getUserBooks(user.getUsername()));
			Page<Post> postPage = postService.findAll(pageable);
			PageWrapper<Post> page = new PageWrapper<Post>(postPage, "/");
			model.addAttribute("page", page);
			List<Double> catIds = new ArrayList<>();
			List<Double> authorVals = new ArrayList<>();
			List<Double> results = new ArrayList<>();
			List<Intrests> intrests = intrestService.findAllByUserId(user.getID());
			for(Intrests intr : intrests) {
				catIds.add(new Double(intr.getCategoryId()));
				authorVals.add(new Double(intr.getAuthorValue()));
				results.add(new Double(1));
			}
			/*catIds.forEach(d -> System.out.println(d));
			authorVals.forEach(d -> System.out.println(d));
			results.forEach(d -> System.out.println(d));*/
		/*	List<Double> badCatIds = getBadCatIds(catIds);
			List<Double> badAuthorVals = getBadAuthorsVals(authorVals, badCatIds.size());
			catIds.addAll(badCatIds);
			authorVals.addAll(badAuthorVals);
			for(int i = 0 ; i < badCatIds.size() ; i++) {
				results.add(new Double(0));
			}*/
			catIds.add(new Double(12));
			authorVals.add(new Double(333));
			results.add(new Double(0));
			System.out.println(1);
			NeuralNetwork net = createNeuralNet(catIds,authorVals,results);
			System.out.println(2);
			model.addAttribute("postPage", clearAndSortPostList(postPage.getContent(), user.getUsername(), net));
			System.out.println(3);
			List<Category> cat = catService.findAll();
			model.addAttribute("categories", cat);
			model.addAttribute("catName", catName);
		
			
		}
		
		model.addAttribute("book", new Book());
		model.addAttribute("post", new Post());
		
	
		return "index";
	}
	
	private List<Double> getBadCatIds(List<Double> catIds) {
		List<Double> badCatIds = new ArrayList<>();
		for(Category cat : catService.findAll()) {
			if(!checkIfListContainsElement(catIds, new Double(cat.getId()))) {
				badCatIds.add(new Double(cat.getId()));
			}
		}
		return badCatIds;
	}
	
	private List<Double> getBadAuthorsVals(List<Double> authorVals, int catIdsSize ) {
		List<Double> badCatIds = new ArrayList<>();
		for(Author aut : authorService.findAll()) {
			if(!checkIfListContainsElement(authorVals, new Double(aut.getAuthorValue()))) {
				badCatIds.add(new Double(aut.getAuthorValue()));
			}
		}
		while(badCatIds.size() < catIdsSize) {
			Random r = new Random();
			badCatIds.add(r.nextDouble());
		}
		return badCatIds;
	}
	
	private boolean checkIfListContainsElement(List<Double> list, Double value) {
		for(Double val : list) {
			if(val.doubleValue() == value.doubleValue()) {
				return true;
			}
		}
		return false;
	}
	
	public NeuralNetwork createNeuralNet(List<Double> catIds, List<Double> authorVals, List<Double> results) {
		NeuralNetwork neuralNetwork = new Perceptron(2, 1);
		System.out.println(11);
		DataSet trainingSet = 
                new  DataSet(2, 1); 
		System.out.println(12);
		for(int i = 0 ; i < catIds.size() ; i++) {
			System.out.println(1+"a" + i);
			trainingSet.
			addRow (new DataSetRow (new double[]{catIds.get(i).doubleValue(), authorVals.get(i).doubleValue()}, 
			 new double[]{results.get(i).doubleValue()})); 
			System.out.println(1+"b" + i);
		}
		System.out.println(139);
		neuralNetwork.learn(trainingSet);
		System.out.println(13);
		return neuralNetwork;
		
	}

	private List<Post> clearAndSortPostList(List<Post> posts, String userName, NeuralNetwork net) {
		List<Post> output = new ArrayList<>();
		for (Post post : posts) {
			if (!checkLocationForUser(userService.findByName((post.getAuthorName())))
					&& !post.getAuthorName().equals(userName)) {
				if(post.isBookPost()) {
					System.out.println(22);
					String text = post.getText();
					Double catVal = new Double(catService.findByCategoryName(post.getBookCategory()).getId());			
				//	System.out.println(catVal);
					String authorName = text.substring(text.indexOf(WRITTEN_BY) + WRITTEN_BY.length(), text.lastIndexOf(" "));
					String authorLastName = text.substring(text.lastIndexOf(" ") + 1);	
					Double authorVal = new Double(countStringAsciValue(authorName,authorLastName));
					System.out.println(23);
				//	System.out.println(authorVal);
					net.setInput(catVal.doubleValue(), authorVal.doubleValue());
					System.out.println(24);
					net.calculate();
					System.out.println(25);
				//	System.out.println(net.getOutput()[net.getOutput().length - 1]);
					if(net.getOutput()[net.getOutput().length - 1] == 1) {
						output.add(post);
					}
					System.out.println(26);
				}else {
					output.add(post);
				}
			
				
				
			}
		}
		output.sort((Post b, Post b2) -> {
			if (b.getDateTime().after(b2.getDateTime())) {
				return -1;
			} else if (b.getDateTime().before(b2.getDateTime())) {
				return 1;
			} else {
				return 0;
			}
		});
		return output;
	}

	private boolean checkLocationForUser(UserData user) {
		return GetLocation.distFromInMeters(Double.parseDouble(currentLocation.getLatitude()),
				Double.parseDouble(currentLocation.getLongitude()), Double.parseDouble(user.getLatitude()),
				Double.parseDouble(user.getLongitude())) > 30000;
	}

	private void setUserGeoLocation(UserData user) {
		currentLocation = GetLocation.getLocation();
		user.setCity(currentLocation.getCity());
		user.setLongitude(currentLocation.getLongitude());
		user.setLatitude(currentLocation.getLatitude());
		userService.update(user);
	}

	@RequestMapping(value = "/home/page/{id}", method = RequestMethod.GET)
	public String historyDetail(Model model, @PathVariable long id, Pageable p) {

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
		java.util.Date today = new java.util.Date();

		book.setDateTime(new java.sql.Timestamp(today.getTime()));
		Post post = new Post();
		book.setBookCategoryId(catService.findByCategoryName(book.getCategory()).getId());
		post.setBookCategory(book.getCategory());
		post.setBookCategoryId(book.getBookCategoryId());
		post.setBookPost(true);
		Author author = new Author();
		author.setAuthorName(book.getAuthorName());
		author.setAuthorLastName(book.getAuthorLastName());
		author.setAuthorValue(countStringAsciValue(book.getAuthorName(), book.getAuthorLastName()));
		authorService.save(author);
		Intrests intrest = new Intrests();
		intrest.setCategoryId(book.getBookCategoryId());
		intrest.setUserId(owner.getID());
		intrest.setAuthorValue(countStringAsciValue(book.getAuthorName(), book.getAuthorLastName()));
		intrestService.save(intrest);
		bookService.save(book);

		post.setImage(book.getImage());
		post.setAuthorName(owner.getUsername());
		post.setAuthorImage(owner.getImage());
		post.setDateTime(new java.sql.Timestamp(today.getTime()));

		String text = "Hi, I would like to offer book " + book.getTitle() + " written by " + book.getAuthorName() + " "
				+ book.getAuthorLastName();

		post.setText(text);
		postService.save(post);

		return "redirect:/";
	}

	@GetMapping(value = "/home/{username}")
	public String showBooks(@PathVariable String username, RedirectAttributes redirect) {
		redirect.addFlashAttribute("userBooks", username);
		return "redirect:/userbooks";
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
	@PostMapping(value = "/home/publishPost")
	public String publishPost(@ModelAttribute("post") Post post, Model model) {
		UserData user = (UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		post.setAuthorName(user.getUsername());
		java.util.Date today = new java.util.Date();
		post.setDateTime(new java.sql.Timestamp(today.getTime()));
		post.setAuthorImage(user.getImage());
		postService.save(post);

		return "redirect:/";
	}

	@GetMapping(value = "/home/intrest/{catName}")
	public String showIntrests(@PathVariable String catName) {
		UserData owner = (UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Intrests intrest = new Intrests();
		String categoryName = catName.substring(0, catName.indexOf("sep"));
		String authorName = catName.substring(catName.indexOf(WRITTEN_BY) + WRITTEN_BY.length(), catName.lastIndexOf(" "));
		String authorLastName = catName.substring(catName.lastIndexOf(" ") + 1);	
		intrest.setCategoryId(catService.findByCategoryName(categoryName).getId());
		intrest.setUserId(owner.getID());
		intrest.setAuthorValue(countStringAsciValue(authorName,authorLastName));
		intrestService.save(intrest);
		return "redirect:/";
	}
}
