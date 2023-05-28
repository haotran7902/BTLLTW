package com.samsung.ltw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.samsung.ltw.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("SELECT p FROM User p WHERE NOT EXISTS (SELECT r FROM p.roles r WHERE r.name = 'ADMIN')")
	List<User> getAllsExceptAdmin();
	
	@Query("select p from User p where p.username = ?1")
	User getAccountsByUsername(String username);
	
	@Query("select p from User p where p.username = ?1 and p.password = ?2")
	User getAccount(String username, String password);
	
	@Modifying
    @Query(value = "DELETE FROM user_role WHERE user_id = ?1", nativeQuery = true)
	void deleteUserIDFromUserRole(Integer user_id);
	
}
