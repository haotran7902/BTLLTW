package com.samsung.ltw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samsung.ltw.entity.Book;
import com.samsung.ltw.repository.BookRepository;
import com.samsung.ltw.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> getAll() {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> getByCateID(Integer category_id) {
		return bookRepository.getByCateID(category_id);
	}
	
	@Override
	public String getCnameByCid(Integer id) {
		return bookRepository.getCategoryName(id);
	}

	@Override
	public Book getByID(Integer id) {
		return bookRepository.findById(id).get();
	}

	@Override
	public List<Book> getBySearch(String txt_search) {
		return bookRepository.getBySearch(txt_search);
	}

	@Override
	public List<Book> getBestSellerBooks() {
		return bookRepository.getBestSeller();
	}

	@Override
	public List<Book> getRelatedBooks(Integer cateID, Integer book_id) {
		return bookRepository.getRelatedBooks(cateID, book_id);
	}

	@Override
	public void saveBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public void deleteBook(Integer id) {
		bookRepository.delete(bookRepository.findById(id).get());
	}

	

}
