package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.Word;
import com.example.app.service.DictService;
import com.example.app.service.WordService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mydictionary/word")
public class EditWordController {

    private final WordService wordService;
    private final DictService dictService;

	@GetMapping("/edit")
    public String showWordForm(
    		@RequestParam(required = false) Long id,
    		HttpSession session,
    		Model model
    ) {
		
		Word word = null;
		
		//idがある　【単語の編集】　/　idがない　【単語の新規登録】
        if (id != null) {
   
        	//idが不正の場合、リダイレクト
        	if (id > wordService.getAll().size()) {
        		return "redirect:/mydictionary/show/all";
        	}
        	
        	//idが問題ない場合、該当のidのword情報を受け取る
        	model.addAttribute("pageTitle", "単語の編集");
        	
        	word = wordService.getWordById(id);
        	
        } else {
        	model.addAttribute("pageTitle", "新規単語登録");
        }
        
        	
	        session.setAttribute("word", word);	//今後使うかも
	        
	        //word_form表示用
	        model.addAttribute("word", word);
	        model.addAttribute("dictList", dictService.getAll());
	        
	        System.out.println("**************チェック**************");
	        System.out.println(dictService.getAll());
            System.out.println(word);

        // 新規追加と編集の両方で同じフォームを使うので、一つのフォームを表示する
        return "edit/word_form";
    }

	@PostMapping("/confirm")
	public String showConfirmationPage(@ModelAttribute Word word) {
	    // Wordオブジェクトを確認画面に渡す
	    
	    System.out.println(word);
	    return "confirmation_page";
	}

    @PostMapping("/delete/{id}")
    public String deleteWord(@PathVariable Long id) {
        // 単語の削除処理を行う
        // 削除が完了したらリダイレクト先を適切なものにする
        return "redirect:/show/all";
	}

}
