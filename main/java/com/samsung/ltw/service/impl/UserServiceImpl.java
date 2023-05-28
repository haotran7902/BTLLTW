package com.samsung.ltw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samsung.ltw.entity.User;
import com.samsung.ltw.repository.UserRepository;
import com.samsung.ltw.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getAllsExceptAdmin() {
		return userRepository.getAllsExceptAdmin();
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.getAccountsByUsername(username);
	}

	@Override
	public boolean isExisted(User user) {
		User user2 = userRepository.getAccountsByUsername(user.getUsername());
		if(user2 != null) return true;
		return false;
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public User getAccount(String username, String password) {
		return userRepository.getAccount(username, password);
	}

	@Override
	public void delete(Integer user_id) {
		userRepository.delete(userRepository.findById(user_id).get());
	}
	
	@Transactional
	@Override
	public void deleteUserIDFromUserRole(Integer user_id) {
		userRepository.deleteUserIDFromUserRole(user_id);
	}
}
