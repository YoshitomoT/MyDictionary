package com.example.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	

    


	@GetMapping({"/add", "/edit"})
    public String editWordForm(
    		@RequestParam(required = false) Long id,
    		HttpSession session,
    		Model model
    ) {
		
		Word word = new Word();
		
		//idがある　【単語の編集】　/　idがない　【単語の新規登録】
        if (id != null) {
   
//        	//idが不正の場合、リダイレクト
//        	if (id > wordService.getAll().size()) {
//        		System.out.println(wordService.getAll().size() + "しかないよ");
//        		return "redirect:/mydictionary/show/all";
//        	}
        	
        	//idが問題ない場合、該当のidのword情報を受け取る
        	model.addAttribute("pageTitle", "単語の編集");
        	word = wordService.getWordById(id);
        	
        } else {
        	model.addAttribute("pageTitle", "新規単語登録");
        	System.out.println(word);	//デバック用
        	model.addAttribute("word", word);
	        model.addAttribute("dictList", dictService.getAll());
	        return "edit/add_word_form";
	        
        }
        
	        //word_form表示用
	        model.addAttribute("word", word);
	        model.addAttribute("dictList", dictService.getAll());
	        
//	        System.out.println("**************【チェック】単語編集ページ表示前*******************");
//	        System.out.println("dictService.getAll()->" + dictService.getAll());
//          System.out.println("word->" + word);
//          System.out.println("********************************************************");

        // 新規追加と編集の両方で同じフォームを使うので、一つのフォームを表示する
        return "edit/word_form";
    }

	
//	編集した単語の登録を行うメソッド
	@PostMapping("/add")
	public String registerAddWord(
			@ModelAttribute Word addWord,
			@RequestParam(name ="registeredDictIdList", required = false) List<Integer> addDictIdList,
			RedirectAttributes rs
			) {
		
	    if (addDictIdList == null) {
	        addDictIdList = new ArrayList<>();
	    }
		
		System.out.println("**************【チェック】編集ページからのパラメーター*******************");
		System.out.println("addDictIdList->" + addDictIdList);
		System.out.println("addWord->" + addWord);
		System.out.println("***********************************************************");
		
		//Wordsテーブルの更新処理(単語の追加)
		wordService.setNewWord(addWord);
		//dictionary_wordテーブルの更新に必要な新規登録した単語のID情報を取得
		Long newWordId = wordService.getLastInsertedId();
		
		System.out.println("newWordId->" + newWordId);	//デバック用
		
	    //dictionary_wordテーブルの更新処理（単語が登録している辞典情報）
	    dictWordService.setDictWord(newWordId, addDictIdList);
	
		//一覧ページに表示するフラッシュメッセージの格納
		rs.addFlashAttribute("statusMessage", "単語「" + addWord.getName() + "」を登録しました。");
		
		// リダイレクト先を指定
		return "redirect:/mydictionary/show/all"; 
	}
	
	
//	編集した単語の登録を行うメソッド
	@PostMapping("/register")
	public String registerEditedWord(
			@ModelAttribute Word editedWord,
			@RequestParam(name ="registeredDictIdList", required = false) List<Integer> editedDictIdList,
			RedirectAttributes rs
	) {
		
	    if (editedDictIdList == null) {
	    	editedDictIdList = new ArrayList<>();
	    }
		
		
		System.out.println("**************【チェック】編集ページからのパラメーター*******************");
		System.out.println("editedDictIdList->" + editedDictIdList);
		System.out.println("editedWord->" + editedWord);
        System.out.println("***********************************************************");
        
		//Wordテーブル（登録している辞典以外の情報）の更新処理
        wordService.setEditedWord(editedWord);
        
	    
	    //Wordｓテーブル（登録している辞典の情報）の更新
	    dictWordService.setDictWord(editedWord.getId(), editedDictIdList);
	    
	    //一覧ページに表示するフラッシュメッセージの格納
	    rs.addFlashAttribute("statusMessage", "単語「" + editedWord.getName() + "」を更新しました。");
	    
	    // リダイレクト先を指定
	    return "redirect:/mydictionary/show/all"; 
	}

	
	
//	単語の削除を行うメソッド
    @GetMapping("/delete")
    public String deleteWord(
    		@RequestParam(required = false) Long id,
    		RedirectAttributes rs
    ) {
    	
    	//単語情報取得
    	Word word = wordService.getWordById(id);
    	
        // Wordsテーブル内、単語の削除処理
    	wordService.deleteWordById(id);
    	// Wordsテーブル内、単語が登録している辞典の情報の削除
    	dictWordService.deleteDictWordById(id);
    	
    	
    	//一覧ページに表示するフラッシュメッセージの格納
	    rs.addFlashAttribute("statusMessage", "単語「" + word.getName() + "」を削除しました。");
    	
    	//リダイレクト先を指定
	    return "redirect:/mydictionary/show/all"; 
	}

}
