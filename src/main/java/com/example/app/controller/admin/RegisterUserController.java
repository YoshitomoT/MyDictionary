package com.example.app.controller.admin;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.User;
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
