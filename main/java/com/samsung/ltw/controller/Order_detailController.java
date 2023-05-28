package com.samsung.ltw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.DecimalFormat;
import java.text.Format;

import com.samsung.ltw.entity.Book;
import com.samsung.ltw.entity.Order_detail;
import com.samsung.ltw.entity.User;
import com.samsung.ltw.service.BookService;
import com.samsung.ltw.service.Order_detailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class Order_detailController {
	
	@Autowired
	private Order_detailService order_detailService;
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/save/order-detail")
	public String saveOrderDetail(@RequestParam("book_id") Integer book_id,
			@RequestParam("quantity") int quantity, HttpSession session) {
		Book book = bookService.getByID(book_id);
		book.setSold(book.getSold() + quantity);
		bookService.saveBook(book); // cap nhat lai so luong da ban
		
		int total = book.getPrice() * quantity;
		User user = (User) session.getAttribute("userLogin");
		Order_detail order_detail = new Order_detail();
		order_detail.setUser(user);
		order_detail.setBook(book);
		order_detail.setQuantity(quantity);
		order_detail.setTotal(total);
		
		Order_detail order_detail2 = order_detailService.getOrderByUserIDAndBookID(user.getUser_id(), book_id);
		if(order_detail2 != null) {
			order_detail2.setQuantity(quantity);
			order_detail2.setTotal(total);
			order_detailService.save(order_detail2);
			System.err.println(order_detail2);
			return "redirect:/detail/" + String.valueOf(book_id);
		}
		System.err.println(order_detail);
		order_detailService.save(order_detail);
		return "redirect:/detail/" + String.valueOf(book_id);
	}
	
	@PostMapping("/save/order-detail-1")
	public String saveOrderDetailHome(@RequestParam("book_id") Integer book_id,
			@RequestParam("quantity") int quantity, HttpSession session) {
		Book book = bookService.getByID(book_id);
		book.setSold(book.getSold() + quantity);
		bookService.saveBook(book); // cap nhat lai so luong da ban
		
		int total = book.getPrice() * quantity;
		User user = (User) session.getAttribute("userLogin");
		Order_detail order_detail = new Order_detail();
		order_detail.setUser(user);
		order_detail.setBook(book);
		order_detail.setQuantity(quantity);
		order_detail.setTotal(total);
		
		Order_detail order_detail2 = order_detailService.getOrderByUserIDAndBookID(user.getUser_id(), book_id);
		if(order_detail2 != null) {
			order_detail2.setQuantity(quantity);
			order_detail2.setTotal(total);
			order_detailService.save(order_detail2);
			System.err.println(order_detail2);
			return "redirect:/cart";
		}
		System.err.println(order_detail);
		order_detailService.save(order_detail);
		return "redirect:/cart";
	}
	
	@GetMapping("/cart")
	public String goToCart(HttpSession session, Model model) {
		
		User user = (User) session.getAttribute("userLogin");
		if(user != null) {
			List<Order_detail> order_details = order_detailService.getOrderByUserID(user.getUser_id());
			int totalCost = 0;
			for(Order_detail o: order_details) {
				totalCost += o.getTotal();
			}
			Format decimalFormat = new DecimalFormat("#,###,###,###");
	        String formattedTotalCost = decimalFormat.format(totalCost);
			model.addAttribute("totalCost", formattedTotalCost);
			model.addAttribute("carts", order_details);
			return "Cart";
		}
		return "403";
	}
	
	@DeleteMapping("/cart/delete")
	public String deleteCart(@RequestParam("book_id") Integer book_id, HttpSession session) {
		User user = (User) session.getAttribute("userLogin");
		int quantity = order_detailService.getOrderByUserIDAndBookID(user.getUser_id(), book_id).getQuantity();
		Book book = bookService.getByID(book_id);
		book.setSold(book.getSold() - quantity);
		bookService.saveBook(book); // cap nhat lai so luong da ban sau khi xoa
		
		if(user != null) {
			order_detailService.deleteByUserIdAndBookId(user.getUser_id(), book_id);
			return "redirect:/cart";
		}
		return "403";
	}
}
