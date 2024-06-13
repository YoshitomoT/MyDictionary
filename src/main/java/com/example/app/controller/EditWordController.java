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

	
//================== 単語の新規登録 ==================
	
	//単語の新規登録フォーム表示のメソッド
    @GetMapping("/add")
    public String addWordForm(Model model, HttpSession session) {
    	
       	// セッションからログインユーザーのIDを取得
    	Integer userId = (Integer) session.getAttribute("userId");
    	model.addAttribute("pageTitle", "新規単語登録");
    	model.addAttribute("word", new Word());
    	model.addAttribute("dictList", dictService.getAll(userId));
    	return "edit/add_word_form";
    }
    
    //単語の新規登録処理用メソッド
	@PostMapping("/add")
	public String registerAddWord(
			@ModelAttribute Word word,
			@RequestParam(name ="registeredDictIdList", required = false) List<Integer> addDictIdList,
			HttpSession session,
			RedirectAttributes rs
			) {
		
       	// セッションからログインユーザーのIDを取得
    	Integer userId = (Integer) session.getAttribute("userId");
		
		//登録辞書IDリストがnullの場合は空リストを初期化
	    if (addDictIdList == null) {
	        addDictIdList = new ArrayList<>();
	        addDictIdList.add(99);
	    }
	    
		//Wordsテーブルに単語を登録
		wordService.setNewWord(userId, word);
		//新規登録した単語のID情報を取得
		Long newWordId = wordService.getLastInsertedId();
		
	    //dictionary_wordテーブルの更新処理（辞書と単語の関連付け処理）
	    dictWordService.setDictWord(newWordId, addDictIdList);
	
		//フラッシュメッセージの設定
		rs.addFlashAttribute("statusMessage", "単語「" + word.getName() + "」を登録しました。");
		
		// リダイレクト先を指定
		return "redirect:/mydictionary/show/all"; 
	}

	
//================== 単語の編集 ================== 
	
	//単語の編集フォーム表示用のメソッド
    @GetMapping("/edit")
    public String editWordForm(@RequestParam Long id, Model model, HttpSession session) {
       	// セッションからログインユーザーのIDを取得
    	Integer userId = (Integer) session.getAttribute("userId");
        Word word = wordService.getWordById(id);
        model.addAttribute("pageTitle", "単語の編集");
        model.addAttribute("word", word);
        model.addAttribute("dictList", dictService.getAll(userId));
        return "edit/word_form";
    }

    //編集した単語の登録を行うメソッド
	@PostMapping("/register")
	public String registerEditedWord(
			@ModelAttribute Word editedWord,
			@RequestParam(name ="registeredDictIdList", required = false) List<Integer> editedDictIdList,
			RedirectAttributes rs
	) {
		//登録辞書IDリストがnullの場合は空リストを初期化
	    if (editedDictIdList == null) {
	    	editedDictIdList = new ArrayList<>();
	    	editedDictIdList.add(99);
	    }

		//Wordテーブルの更新処理（登録している辞典情報以外の情報）
        wordService.setEditedWord(editedWord);
         
	    //dictionary_Wordテーブルの更新（登録している辞典情報）
	    dictWordService.setDictWord(editedWord.getId(), editedDictIdList);
	    
	    //フラッシュメッセージの設定
	    rs.addFlashAttribute("statusMessage", "単語「" + editedWord.getName() + "」を更新しました。");
	    
	    // リダイレクト先を指定
	    return "redirect:/mydictionary/show/all"; 
	}

	
//================== 単語の削除==================	
	
	//単語の削除を行うメソッド
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
