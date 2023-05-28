package com.samsung.ltw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.samsung.ltw.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	@Query("select c from Comment c where c.book.book_id = ?1 ORDER BY c.comment_id DESC")
	List<Comment> getCommentByBook_id(Integer book_id);
	
	@Modifying
    @Query(value = "DELETE FROM comment WHERE user_id = ?1", nativeQuery = true)
    void deleteByUser_id(Integer user_id);
	
	@Modifying
    @Query(value = "DELETE FROM comment WHERE book_id = ?1", nativeQuery = true)
    void deleteByBook_id(Integer book_id);
}
