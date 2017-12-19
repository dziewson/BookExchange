package com.p.lodz.pl.bookexchange.services;

import java.util.List;

import com.p.lodz.pl.bookexchange.entities.Author;

public interface AuthorService {
	public void save(Author author);
	public List<Author> findAll();

}
