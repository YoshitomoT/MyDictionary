package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.service.WordService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/words")
public class WordController {
	
	private final WordService wordService;
	
	@GetMapping
	public String showAlls(
			Model model
	) {
		System.out.println(wordService.getAll());
		model.addAttribute("wordList", wordService.getAll());
		//テステス
		return "all_words";
	}
	
	@GetMapping("/{id}/{name}")
	public String showDetail(
			@PathVariable("id") Integer id,
			@PathVariable("name") String name,
			Model model
	) {
		System.out.println(id);
		System.out.println(name);
		// 他の処理やモデルへの追加処理
		return "word_detail";
	}
	
	
	

}
