package com.samsung.ltw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samsung.ltw.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
}
