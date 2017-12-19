package com.p.lodz.pl.bookexchange.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p.lodz.pl.bookexchange.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	public List<Category> findAll();
	public Category findById(long id);
	public Category findByCategoryName(String categoryName);
	
}


