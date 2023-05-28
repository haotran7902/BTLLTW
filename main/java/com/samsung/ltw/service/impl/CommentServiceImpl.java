package com.samsung.ltw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samsung.ltw.entity.Comment;
import com.samsung.ltw.repository.CommentRepository;
import com.samsung.ltw.service.CommentService;

import jakarta.transaction.Transactional;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public List<Comment> getCommentByBook_id(Integer book_id) {
		return commentRepository.getCommentByBook_id(book_id);
	}

	@Override
	public void save(Comment comment) {
		commentRepository.save(comment);
	}
	
	@Transactional
	@Override
	public void deleteCommentByUserID(Integer user_id) {
		commentRepository.deleteByUser_id(user_id);
	}
	
	@Transactional
	@Override
	public void deleteCommentByBookID(Integer book_id) {
		commentRepository.deleteByBook_id(book_id);
	}

}
