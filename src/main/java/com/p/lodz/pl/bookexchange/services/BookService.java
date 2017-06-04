package com.p.lodz.pl.bookexchange.services;

import java.util.List;

import com.p.lodz.pl.bookexchange.entities.Book;

public interface BookService {	

	public Book findByOwner(String owner);
	
	public Book findByTitle(String title);
	
	public Book findByAuthorLastName(String author);
	
	public List<Book> findAll();
	
	public Book save(Book book) ;
	

}
