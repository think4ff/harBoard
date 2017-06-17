package com.har.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.har.domain.User;
import com.har.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	// private List<User> users = new ArrayList<>();

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}

	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		if(user == null) {
			return "redirect:/users/loginForm";
		}
		
		if(!user.matchPassword(password))
				return "redirect:/users/loginForm";
		
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		
		return "redirect:/";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
		return "redirect:/";
	}

	@GetMapping("/form")
	public String userSingUpForm() {
		return "/user/form";
	}

	@PostMapping("")
	public String createUser(User user, Model model) {
		System.out.println("USER_INFO: " + user);
		// users.add(user);
		userRepository.save(user);
		return "redirect:/users";
	}

	@GetMapping("")
	public String list(Model model) {
		// model.addAttribute("users", users);
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}

	@GetMapping("/{id}/updateForm")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session))
			return "redirect:/users/loginForm";
		
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if(!sessionedUser.matchId(id))	
			throw new IllegalStateException("You can't modify another Info!!");
		//or sessionedUser.getId() 로 아래에서 조회해서 실패 여부를 판단. 그러면 if line 삭제 가능
		model.addAttribute("user", userRepository.findOne(id));
//		model.addAttribute("user", userRepository.findOne(sessionedUser.getId()));
		return "/user/updateForm";
	}

	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User modUser, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session))
			return "redirect:/users/loginForm";
		
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if(!sessionedUser.matchId(id))
			throw new IllegalStateException("You can't modify another Info!!");
		
		User currUser = userRepository.findOne(id);
		currUser.update(modUser);
		userRepository.save(currUser);
		return "redirect:/users";
	}

}
