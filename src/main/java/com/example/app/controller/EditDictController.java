package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.Dictionary;
import com.example.app.service.DictService;
import com.example.app.service.DictWordService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mydictionary/dictionary")
public class EditDictController {
	
	private final DictService dictService;
	private final DictWordService dictWordService;

	@GetMapping("/add")
	public String addDictForm(
			Model model
	) {
		model.addAttribute("dictList", dictService.getAll());
		model.addAttribute("dictionary", new Dictionary());
		model.addAttribute("pageTitle", "辞書の新規登録");
		System.out.println("OK!!!!!!!!!!!!!");
		return "edit/add_dictionary_form";
	}
	
	@PostMapping("/add")
	public String registerDict(
			@ModelAttribute Dictionary addDict,
			Model model,
			RedirectAttributes rs
			) {
		
		dictService.registerDict(addDict);
		
		//一覧ページに表示するフラッシュメッセージの格納
		rs.addFlashAttribute("statusMessage", "「" + addDict.getName() + "」という辞典を新しく登録しました。");
		
		// リダイレクト先を指定
		return "redirect:/mydictionary/show/all";
	}
	
	@GetMapping("/delete")
	public String deleteDict(
			@RequestParam("id") Integer dictId,
			RedirectAttributes rs
	) {
		
		//削除する辞書の情報を取得
		Dictionary dict = dictService.getDictById(dictId);
		//dictionarysテーブルから削除
		dictService.deleteDictById(dictId);
		//dict_wordテーブルから辞典idが合致するカラムを削除
		dictWordService.deleteDictWordByDictId(dictId);
		//一覧ページに表示するフラッシュメッセージの格納
		rs.addFlashAttribute("statusMessage", "「" + dict.getName() + "」という辞典を削除しました。");
		// リダイレクト先を指定
		return "redirect:/mydictionary/show/all";
	}


	
	

}
