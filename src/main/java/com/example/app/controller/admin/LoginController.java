package com.example.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.dto.LoginFormDTO;
import com.example.app.dto.UserSessionDTO;
import com.example.app.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mydictionarys")
public class LoginController {
	
	private final UserService userService;
	
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		model.addAttribute("user", new LoginFormDTO());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(
		@ModelAttribute LoginFormDTO loginForm,
		Model model,
		RedirectAttributes rs,
		HttpSession session
	) {
		
		//ログイン認証
		UserSessionDTO userSessionDTO = userService.authenticateUser(loginForm);

		//ログイン失敗時
		if(userSessionDTO == null) {
			model.addAttribute("user", new LoginFormDTO());
			rs.addFlashAttribute("errorMessage", "※ユーザー名かパスワードが違います。");
			return "redirect:/";
		}
		
		session.setAttribute("user", userSessionDTO);
		System.out.println("ログイン情報" + userSessionDTO);
		return "redirect:/mydictionarys/show/all";
		
	}
	
	@PostMapping("/logout")
    public String logout(HttpSession session) {
        // セッションを無効化する
        session.invalidate();

        // ログアウト後にホームページにリダイレクト
        return "redirect:/";
    }
	
	@GetMapping("/timeout")
	public String timeout() {
		return "timeout";
	}
	
	
	
	

}
