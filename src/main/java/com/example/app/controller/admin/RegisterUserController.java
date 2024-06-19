package com.example.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.dto.UserRegistrationDTO;
import com.example.app.exception.UserRegistrationException.PasswordsNotMatchingException;
import com.example.app.exception.UserRegistrationException.UserAlreadyExistsException;
import com.example.app.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mydictionarys")
public class RegisterUserController {

    private final UserService userService;

	@GetMapping("/registerUser")
    public String showRegisterUserForm(Model model) {
    	model.addAttribute("newUser", new UserRegistrationDTO());
        return "register_user";
    }
    
	@PostMapping("/registerUser")
	public String registerUser(
	        @Valid @ModelAttribute("userForm") UserRegistrationDTO userForm, 
	        BindingResult bindingResult,
	        RedirectAttributes redirectAttributes) throws Exception {

	    try {
	        // パスワードの一致チェックとユーザーの登録
	        userService.registerNewUser(userForm);
	        redirectAttributes.addFlashAttribute("statusMessage", "ユーザー登録完了しました。");
	        return "redirect:/mydictionarys/login"; // 登録成功時はログインページにリダイレクト

	    } catch (PasswordsNotMatchingException e) {
	        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
	        return "redirect:/mydictionarys/registerUser"; // エラーがある場合は登録ページにリダイレクト

	    } catch (UserAlreadyExistsException e) {
	        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
	        return "redirect:/mydictionarys/registerUser"; // エラーがある場合は登録ページにリダイレクト
	    }
	}


}