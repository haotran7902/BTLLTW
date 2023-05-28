package com.samsung.ltw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samsung.ltw.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	@Query("select p from Book p where p.category.category_id = ?1")
	List<Book> getByCateID(Integer cateID);
	
	@Query("select p from Book p where p.title like %?1%")
	List<Book> getBySearch(String txt_search);
	
	@Query("SELECT distinct b.category.name from Book b where b.category.category_id = ?1")
	String getCategoryName(Integer id);
	
	@Query("select b from Book b order by b.sold desc")
	List<Book> getBestSeller();
	
	@Query("select p from Book p where p.category.category_id = ?1 and p.book_id != ?2")
	List<Book> getRelatedBooks(Integer cateID, Integer book_id);
}

