package com.samsung.ltw.service;

import java.util.List;

import com.samsung.ltw.entity.Comment;

public interface CommentService {
	
	List<Comment> getCommentByBook_id(Integer book_id);
	
	void save(Comment comment);
	
	void deleteCommentByUserID(Integer user_id);
	
	void deleteCommentByBookID(Integer book_id);
}
