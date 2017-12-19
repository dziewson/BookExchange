package com.p.lodz.pl.bookexchange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p.lodz.pl.bookexchange.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{
	Author findByAuthorNameAndAuthorLastName(String authorName, String authorLastName);
}
