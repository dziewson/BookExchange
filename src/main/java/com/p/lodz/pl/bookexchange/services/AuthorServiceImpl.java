package com.p.lodz.pl.bookexchange.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p.lodz.pl.bookexchange.entities.Author;
import com.p.lodz.pl.bookexchange.repositories.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	AuthorRepository authorRepository;

	@Override
	public void save(Author author) {
		if(authorRepository.findByAuthorNameAndAuthorLastName(author.getAuthor()) == null) {
			authorRepository.save(author);
		}
	}

	@Override
	public List<Author> findAll() {
		return authorRepository.findAll();
	}

}
