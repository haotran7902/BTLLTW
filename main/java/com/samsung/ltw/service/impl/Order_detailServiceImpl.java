package com.samsung.ltw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samsung.ltw.entity.Order_detail;
import com.samsung.ltw.repository.Order_detailRepository;
import com.samsung.ltw.service.Order_detailService;

import jakarta.transaction.Transactional;

@Service
public class Order_detailServiceImpl implements Order_detailService {
	
	@Autowired
	private Order_detailRepository order_detailRepository;

	@Override
	public List<Order_detail> getOrderByUserID(Integer user_id) {
		return order_detailRepository.getOrderByUserID(user_id);
	}
	
	@Override
	public Order_detail getOrderByUserIDAndBookID(Integer user_id, Integer book_id) {
		return order_detailRepository.getOrderByUserIDAndBookID(user_id, book_id);
	}

	@Override
	public void save(Order_detail order_detail) {
		order_detailRepository.save(order_detail);
	}
	
	@Transactional
	@Override
	public void deleteByUser_id(Integer user_id) {
		order_detailRepository.deleteByUser_id(user_id);
	}
	
	@Transactional
	@Override
	public void deleteByBook_id(Integer book_id) {
		order_detailRepository.deleteByBook_id(book_id);
	}
	
	@Transactional
	@Override
	public void deleteByUserIdAndBookId(Integer user_id, Integer book_id) {
		order_detailRepository.deleteByUserIdAndBookId(user_id, book_id);
	}

}
