package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.User;
import com.example.app.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final UserService userService;
	
	@GetMapping
	public String showHome(Model model) {
		model.addAttribute("user", new User());
		return "home";
	}
	
	@PostMapping("/login")
	public String login(
		@ModelAttribute User user,
		HttpSession session
	) {
		
		//ログイン認証
		if(!userService.isCorrectUserNameAndPassword
				(user.getUserName(), user.getPassword())) {
			return "home";
		}
		
		user.setId(userService.getIdByUser(user));
		session.setAttribute("userId", userService.getIdByUser(user));
		System.out.println("ログイン情報" + user);
		return "redirect:/mydictionary/show/all";
		
	}
	
	@PostMapping("/logout")
    public String logout(HttpSession session) {
        // セッションを無効化する
        session.invalidate();

        // ログアウト後にホームページにリダイレクト
        return "redirect:/";
    }
	
	
	
	

}
