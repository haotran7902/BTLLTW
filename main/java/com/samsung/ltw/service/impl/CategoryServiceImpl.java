package com.samsung.ltw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samsung.ltw.entity.Category;
import com.samsung.ltw.repository.CategoryRepository;
import com.samsung.ltw.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

}
