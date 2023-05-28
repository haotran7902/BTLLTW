package com.samsung.ltw.service;

import java.util.List;

import com.samsung.ltw.entity.Order_detail;

public interface Order_detailService {
	
	List<Order_detail> getOrderByUserID(Integer user_id);
	
	Order_detail getOrderByUserIDAndBookID(Integer user_id, Integer book_id);
	
	void save(Order_detail order_detail);
	
	void deleteByUser_id(Integer user_id);
	
	void deleteByBook_id(Integer book_id);
	
	void deleteByUserIdAndBookId(Integer user_id, Integer book_id);
}
