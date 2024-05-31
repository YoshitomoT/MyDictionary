package com.example.app.controller;

import java.util.List;

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
import com.example.app.service.DictWordService;
import com.example.app.service.WordService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mydictionary/word")
public class EditWordController {

    private final WordService wordService;
    private final DictService dictService;
	private final DictWordService dictWordService;
    


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
	        
	        System.out.println("**************【チェック】単語編集ページ表示前*******************");
	        System.out.println("dictService.getAll()->" + dictService.getAll());
            System.out.println("word->" + word);
            System.out.println("********************************************************");

        // 新規追加と編集の両方で同じフォームを使うので、一つのフォームを表示する
        return "edit/word_form";
    }

	
//	編集した単語の登録を行うメソッド
	@PostMapping("/register")
	public String registerEditedWord(
			@ModelAttribute Word editedWord,
			@RequestParam(name ="registeredDictIdList") List<Integer> editedDictIdList
	) {
		
		System.out.println("**************【チェック】編集ページからのパラメーター*******************");
		System.out.println("editedDictIdList->" + editedDictIdList);
		System.out.println("editedWord->" + editedWord);
        System.out.println("***********************************************************");
        
		//Wordテーブル（登録している辞典以外の情報）の更新処理
        wordService.setEditedWord(editedWord);
        
	    
	    //Wordｓテーブル（登録している辞典の情報）の更新
	    dictWordService.setDictWord(editedWord.getId(), editedDictIdList);
	    
	    
	    return "confirmation_page";
	}

	
	
	
    @PostMapping("/delete/{id}")
    public String deleteWord(@PathVariable Long id) {
        // 単語の削除処理を行う
        // 削除が完了したらリダイレクト先を適切なものにする
        return "redirect:/show/all";
	}

}
