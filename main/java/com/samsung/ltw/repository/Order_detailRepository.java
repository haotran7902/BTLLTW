package com.samsung.ltw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.samsung.ltw.entity.Order_detail;

public interface Order_detailRepository extends JpaRepository<Order_detail, Integer> {
	
	@Query("select o from Order_detail o where o.user.user_id = ?1")
	List<Order_detail> getOrderByUserID(Integer user_id);
	
	@Query("select o from Order_detail o where o.user.user_id = ?1 and o.book.book_id = ?2")
	Order_detail getOrderByUserIDAndBookID(Integer user_id, Integer book_id);
	
	@Modifying
    @Query(value = "DELETE FROM order_detail WHERE user_id = ?1", nativeQuery = true)
    void deleteByUser_id(Integer user_id);
	
	@Modifying
    @Query(value = "DELETE FROM order_detail WHERE book_id = ?1", nativeQuery = true)
    void deleteByBook_id(Integer book_id);
	
	@Modifying
    @Query(value = "DELETE FROM order_detail WHERE user_id = ?1 and book_id = ?2", nativeQuery = true)
    void deleteByUserIdAndBookId(Integer user_id, Integer book_id);
}
