package com.har.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	// @RequestMapping("/welcome")
	@GetMapping("/callWelcome")
	public String welcome(String userId, String password, String name, String email, Model model) {
		System.out.println("userId is " + userId);
		model.addAttribute("userId", userId);

		return "welcome";
	}
}
