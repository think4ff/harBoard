package com.har.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class userController {
	List<User> users = new ArrayList<>();
	
	@PostMapping("/create")
	public String welcome(User user, Model model) {
		System.out.println(user);
		users.add(user);
		return "index";
	}
	
	@GetMapping("/list")
	public String list() {
		return "list";
	}
	
}
