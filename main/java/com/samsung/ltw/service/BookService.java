package com.samsung.ltw.service;

import java.util.List;

import com.samsung.ltw.entity.Book;

public interface BookService {
	
	List<Book> getAll();
	
	List<Book> getByCateID(Integer category_id);
	
	String getCnameByCid(Integer id);
	
	Book getByID(Integer id);
	
	List<Book> getBySearch(String txt_search);
	
	List<Book> getBestSellerBooks();
	
	List<Book> getRelatedBooks(Integer cateID, Integer book_id);
	
	void saveBook(Book book);
	
	void deleteBook(Integer id);
	
}
