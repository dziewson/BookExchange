package com.p.lodz.pl.bookexchange.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p.lodz.pl.bookexchange.entities.Category;
import com.p.lodz.pl.bookexchange.repositories.CategoryRepository;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepo;

	@Override
	public List<Category> findAll() {
		return categoryRepo.findAll();
	}

	@Override
	public Category findById(long id) {
		return categoryRepo.findById(id);
	}

	@Override
	public Category findByCategoryName(String categoryName) {
		return categoryRepo.findByCategoryName(categoryName);
	}
	
	
}
