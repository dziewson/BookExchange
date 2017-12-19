package com.p.lodz.pl.bookexchange.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p.lodz.pl.bookexchange.entities.Book;

public interface BookRepository  extends JpaRepository<Book, Long> {	
	
	public List<Book> findByOwnerName(String ownerName);
	
	public Book findByTitle(String title);
	
	public Book findByAuthorLastName(String authorLastName);


}

