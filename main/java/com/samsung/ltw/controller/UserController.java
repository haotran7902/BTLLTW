package com.samsung.ltw.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.samsung.ltw.entity.Book;
import com.samsung.ltw.entity.Role;
import com.samsung.ltw.entity.User;
import com.samsung.ltw.repository.RoleRepository;
import com.samsung.ltw.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/login/check")
	public String check(Model model, HttpSession session, User user) {
		User userLogin = userService.getAccount(user.getUsername(), user.getPassword());
		System.out.println(userLogin);
		if(userLogin != null) {
			session.setAttribute("userLogin", userLogin);
			return "redirect:/home";
		}
		model.addAttribute("error", "true");
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
//	    session.removeAttribute("userLogin");
	    session.invalidate();
	    return "redirect:/home";
	}
	
	@GetMapping("/signup")
	public String signupForm(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@PostMapping("/signup/save")
	public String createUser(@RequestParam("repeatpassword") String repass, Model model, User user) {
		
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findByName("GUEST"));
		user.setRoles(roles);
		
		if(!repass.equals(user.getPassword())) {
			model.addAttribute("repeatnotequal", "true");
			return "signup";
		}
		if(userService.isExisted(user) == true) {
			model.addAttribute("existed", "true");
			model.addAttribute("repeatnotequal", "false");
			return "signup";
		}
		userService.save(user);
		return "redirect:/login";
	}

}
