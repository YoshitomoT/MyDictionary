package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.dto.UserSessionDTO;
import com.example.app.service.DictService;
import com.example.app.service.WordService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mysyoko/show")
public class ShowController {

    private final WordService wordService;
	private final DictService dictService;

	
//================== 単語の一覧表示 ==================
    /**
     * 全単語を表示するメソッド。
     * 
     * @param model SpringのModelオブジェクト。ビューにデータを渡すために使用します。
     * @return 表示するビューの名前（"all_words"）。
     */
    
    @GetMapping("/allwords")
    public String showAllWordList(Model model,HttpSession session) {
    	
    	// セッションからログインユーザーのIDを取得
    	Integer userId = ((UserSessionDTO) session.getAttribute("user")).getUserId();
    	
        // すべての単語の情報をリストで取得し、モデルに格納
        model.addAttribute("wordList", wordService.getAll(userId));
        
        //登録している全単語数を取得し、モデルに格納
        model.addAttribute("totalWords", wordService.getTotalWords(userId));
          
        //すべての辞典の情報をリストで取得し、モデルに格納
        model.addAttribute("dictList", dictService.getAll(userId));

        // "all_words"ビューを返します。
        return "all_words";
    }

    
//================== 単語の詳細表示 ==================
    /**
     * 指定されたIDと名前に基づいて単語の詳細を表示するメソッド。
     * 
     * @param id URLパスから取得する単語のID。
     * @param name URLパスから取得する単語の名前。
     * @param model SpringのModelオブジェクト。ビューにデータを渡すために使用します。
     * @return 表示するビューの名前（"word_detail"）。
     */
    @GetMapping("/word{id}/{name}")
    public String showDetail(
            @PathVariable("id") Long id, // パス変数からIDを取得
            @PathVariable("name") String name, // パス変数から名前を取得（未使用）
            Model model) {
    	
    	//　指定されたIDの単語の閲覧回数を更新
    	wordService.setPageViewsForWordById(id);
        
        // 指定されたIDに基づいて単語の詳細を取得し、モデルに追加
        model.addAttribute("word", wordService.getWordById(id));
        
        //モデル格納後、最終閲覧日を更新し、詳細ページからジャンプしたら最終閲覧日がアップロードされた風にする
        wordService.setLastViewedDateForWordById(id);
        
        /** デバッグ用
        *System.out.println(id);
        *System.out.println(name);
        */
        System.out.println(wordService.getWordById(id));
        // "word_detail"ビューを返します。
        return "word_detail";
    }
}
