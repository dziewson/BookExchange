package com.p.lodz.pl.bookexchange.services;

import java.util.List;

import com.p.lodz.pl.bookexchange.entities.Category;

public interface CategoryService {
	public List<Category> findAll();
	public Category findById(long id);
	public Category findByCategoryName(String categoryName);
}
