package com.samsung.ltw.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.exception.ObjectExistedException;
import com.samsung.ltw.entity.Book;
import com.samsung.ltw.entity.Category;
import com.samsung.ltw.entity.User;
import com.samsung.ltw.service.BookService;
import com.samsung.ltw.service.CategoryService;
import com.samsung.ltw.service.CommentService;
import com.samsung.ltw.service.Order_detailService;
import com.samsung.ltw.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private Order_detailService order_detailService;
	
	@GetMapping("/manage/user")
	public String getAllUser(Model model, HttpSession session) {
		User user = (User) session.getAttribute("userLogin");
		if(user != null) {
			model.addAttribute("users", userService.getAllsExceptAdmin());
			return "Manage-user";
		}
		return "403";
	}
	
	@GetMapping("/manage/book")
	public String getAllBook(Model model, HttpSession session) {
		User user = (User) session.getAttribute("userLogin");
		if(user != null) {
			model.addAttribute("books", bookService.getAll());
			return "Manage-book";
		}
		return "403";
	}
	
	@DeleteMapping("/delete/user")
	public String deleteUser(@RequestParam("user_id") Integer user_id, HttpSession session) {
		User user = (User) session.getAttribute("userLogin");
		if(user != null) {
			commentService.deleteCommentByUserID(user_id);
			order_detailService.deleteByUser_id(user_id);
			userService.deleteUserIDFromUserRole(user_id);
			userService.delete(user_id);
			return "redirect:/manage/user";
		}
		return "403";
	}
	
	@DeleteMapping("/delete/book")
	public String deleteBook(@RequestParam("book_id") Integer book_id, HttpSession session) {
		User user = (User) session.getAttribute("userLogin");
		if(user != null) {
			commentService.deleteCommentByBookID(book_id);
			order_detailService.deleteByBook_id(book_id);
			bookService.deleteBook(book_id);
			return "redirect:/manage/book";
		}
		return "403";
	}
	
	@PostMapping("/view-book")
	public String viewBook(Model model, @RequestParam("book_id") Integer book_id, HttpSession session) {
		User user = (User) session.getAttribute("userLogin");
		if(user != null) {
			model.addAttribute("book", bookService.getByID(book_id));
			System.err.println(bookService.getByID(book_id));
			model.addAttribute("listC", categoryService.getAllCategories());
			return "View-book";
		}
		return "403";
	}
	
	@PostMapping("/new-book")
	public String newBookForm(Model model, HttpSession session) {
		User user = (User) session.getAttribute("userLogin");
		if(user != null) {
			model.addAttribute("book", new Book());
			model.addAttribute("listC", categoryService.getAllCategories());
			return "View-book";
		}
		return "403";
	}
	
	@PutMapping("/edit-book")
	public String updateBook(@RequestParam("book_id") Integer book_id, Book book,
			@RequestParam("category_id") Integer category_id,
			@RequestParam(name = "imageLink", required = false) String imageLink, HttpSession session) 
					throws ObjectExistedException, IOException, ParseException{
		User user = (User) session.getAttribute("userLogin");
		if(user != null) {
			Category category = new Category(category_id, bookService.getCnameByCid(category_id));
			book.setCategory(category);
			if(imageLink.length() > 0) book.setImage(imageLink);
			bookService.saveBook(book);
			return "redirect:/manage/book";
		}
		return "403";
	}
	
	@PostMapping("/edit-book")
	public String createBook(@RequestParam("category_id") Integer category_id, Book book, 
			@RequestParam(name = "imageLink", required = false) String imageLink, HttpSession session) {
		User user = (User) session.getAttribute("userLogin");
		if(user != null) {
			Category category = new Category(category_id, bookService.getCnameByCid(category_id));
			book.setCategory(category);
			if(imageLink.length() > 0) book.setImage(imageLink);
			bookService.saveBook(book);
			System.err.println(book);
			return "redirect:/manage/book";
		}
		return "403";
	}
}
