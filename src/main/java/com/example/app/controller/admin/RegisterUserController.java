package com.example.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.dto.UserRegistrationDTO;
import com.example.app.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
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
            Model model) {

        if (!userForm.isPasswordMatching()) {
            bindingResult.rejectValue("confirmPassword", "error.userForm", "パスワードが一致しません");
        }

        if (bindingResult.hasErrors()) {
            return "redirect:/registerUser";
        }
        
        userService.registerNewUser(userForm);

        return "redirect:/login";
    }


}