package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.service.WordService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/words")
public class WordController {
	
	private final WordService wordService;
	
	@GetMapping
	public String allwords(
			Model model
	) {
		System.out.println(wordService.getAll());
		model.addAttribute("wordList", wordService.getAll());
		return "all-words";
	}
	

}
