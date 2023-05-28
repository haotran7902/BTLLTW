package com.samsung.ltw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.samsung.ltw.entity.Book;
import com.samsung.ltw.entity.Comment;
import com.samsung.ltw.entity.Role;
import com.samsung.ltw.entity.User;
import com.samsung.ltw.entity.expand.BookExpand;
import com.samsung.ltw.service.BookService;
import com.samsung.ltw.service.CategoryService;
import com.samsung.ltw.service.CommentService;
import com.samsung.ltw.service.Order_detailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private BookService bookService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private Order_detailService order_detailService;

	@GetMapping({ "/home", "/" })
	public String getAllBooks(Model model, HttpSession session) {

		model.addAttribute("listC", categoryService.getAllCategories()); // add category into page home

		// add list book into page home
		List<BookExpand> listBookExpands0 = new ArrayList<>();
		List<Book> list1 = bookService.getAll();
		for (Book b : list1) {
			listBookExpands0.add(new BookExpand(b, getAverageStar(b)));
		}
		model.addAttribute("listB", listBookExpands0);

		// add list best seller into page home
		List<Book> bestSellerBooks = bookService.getBestSellerBooks();
		int tmpIndex = Math.min(4, bestSellerBooks.size()); // neu index < 4 thi lay theo size() list
		bestSellerBooks = bestSellerBooks.subList(0, tmpIndex);
		List<BookExpand> listBookExpands1 = new ArrayList<>();
		for (Book b : bestSellerBooks) {
			listBookExpands1.add(new BookExpand(b, getAverageStar(b)));
		}
		model.addAttribute("listBS", listBookExpands1);

		User user = (User) session.getAttribute("userLogin");
		if (user != null) {
			model.addAttribute("cart", order_detailService.getOrderByUserID(user.getUser_id()).size());
		} else {
			model.addAttribute("cart", 0);
		}

		return "Home";
	}

	@GetMapping("/category")
	public String getBookByCateID(@RequestParam("cateID") Integer cateID, Model model, HttpSession session) {

		// add list book into page home
		List<BookExpand> listBookExpands0 = new ArrayList<>();
		List<Book> list1 = bookService.getByCateID(cateID);
		for (Book b : list1) {
			listBookExpands0.add(new BookExpand(b, getAverageStar(b)));
		}
		model.addAttribute("listB", listBookExpands0);
		model.addAttribute("cname", bookService.getCnameByCid(cateID));
		model.addAttribute("listC", categoryService.getAllCategories());

		User user = (User) session.getAttribute("userLogin");
		if (user != null) {
			model.addAttribute("cart", order_detailService.getOrderByUserID(user.getUser_id()).size());
		} else {
			model.addAttribute("cart", 0);
		}
		return "Home";
	}

	@PostMapping("/search")
	public String getBookBySearch(@RequestParam("txt") String txt, Model model, HttpSession session) {
		if (txt.equals(""))
			return "redirect:/home";

		model.addAttribute("keyword", txt);
		if (bookService.getBySearch(txt).size() == 0) {
			model.addAttribute("emptyList", "true");
		}
		
		List<BookExpand> listBookExpands0 = new ArrayList<>();
		List<Book> list1 = bookService.getBySearch(txt);
		for (Book b : list1) {
			listBookExpands0.add(new BookExpand(b, getAverageStar(b)));
		}
		model.addAttribute("listB", listBookExpands0);
		model.addAttribute("listC", categoryService.getAllCategories());

		User user = (User) session.getAttribute("userLogin");
		if (user != null) {
			model.addAttribute("cart", order_detailService.getOrderByUserID(user.getUser_id()).size());
		} else {
			model.addAttribute("cart", 0);
		}
		return "Home";
	}

	@GetMapping("/detail/{id}")
	public String getDetailProduct(@PathVariable Integer id, Model model, HttpSession session) {
		Book book = bookService.getByID(id);
		model.addAttribute("detailBook", book);
		model.addAttribute("listC", categoryService.getAllCategories());
		
		List<BookExpand> listBookExpands0 = new ArrayList<>();
		List<Book> relatedBooks = bookService.getRelatedBooks(book.getCategory().getCategory_id(), id);
		int tmpIndex = Math.min(4, relatedBooks.size());
		relatedBooks = relatedBooks.subList(0, tmpIndex);
		for (Book b : relatedBooks) {
			listBookExpands0.add(new BookExpand(b, getAverageStar(b)));
		}
		model.addAttribute("relatedBooks", listBookExpands0);

		setAverageStar(model, id);

		User user = (User) session.getAttribute("userLogin");
		if (user != null) {
			model.addAttribute("cart", order_detailService.getOrderByUserID(user.getUser_id()).size());
		} else {
			model.addAttribute("cart", 0);
		}
		return "Detail";
	}

	@GetMapping("/manage")
	public String managePage(HttpSession session) {
		User user = (User) session.getAttribute("userLogin");
		if(user == null) return "403";
		
		for(Role r: user.getRoles()) {
			if(r.getName().equalsIgnoreCase("ADMIN")) return "Manage";
		}
		return "403";

//		return "Manage";
	}

	public void setAverageStar(Model model, Integer id) {
		List<Comment> comments = commentService.getCommentByBook_id(id);
		int totalStar = 0;
		for (Comment comment : comments) {
			totalStar += comment.getStar();
		}
		model.addAttribute("comments", comments);
		if (comments.size() > 0) {
			model.addAttribute("averageStar", (double) Math.round(10 * totalStar / comments.size()) / 10);
			System.out.println((double) Math.round(10 * totalStar / comments.size()) / 10);
		}
	}

	public String getAverageStar(Book book) {
		List<Comment> comments = commentService.getCommentByBook_id(book.getBook_id());
		int totalStar = 0;
		for (Comment comment : comments) {
			totalStar += comment.getStar();
		}
		if (comments.size() > 0) {
			return String.valueOf((double) Math.round(10 * totalStar / comments.size()) / 10) + "/5 ";
		}
		return "Chua ban";
	}
}
