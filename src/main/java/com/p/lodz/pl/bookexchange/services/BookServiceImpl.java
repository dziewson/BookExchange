package com.p.lodz.pl.bookexchange.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p.lodz.pl.bookexchange.entities.Book;
import com.p.lodz.pl.bookexchange.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepo;

	@Override
	public Book findByOwner(String ownerName) {
		return bookRepo.findByOwnerName(ownerName);
	}

	@Override
	public Book findByTitle(String title) {
		return bookRepo.findByTitle(title);
	}

	@Override
	public Book findByAuthorLastName(String authorLastName) {
		return bookRepo.findByAuthorLastName(authorLastName);
	}

	@Override
	public List<Book> findAll() {
		return bookRepo.findAll();
	}

	@Override
	public Book save(Book book) {
		return bookRepo.save(book);
	}

}
