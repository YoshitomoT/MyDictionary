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

    // WordServiceのインスタンスをDI（依存性注入）によって取得します。
    private final WordService wordService;

    /**
     * 全単語を表示するメソッド。
     * 
     * @param model SpringのModelオブジェクト。ビューにデータを渡すために使用します。
     * @return 表示するビューの名前（"all_words"）。
     */
    
    @GetMapping
    public String showAlls(Model model) {
        // 全単語を取得し、コンソールに出力します（デバッグ用）。
        System.out.println(wordService.getAll());
        
        // 取得した全単語リストをモデルに追加します。
        model.addAttribute("wordList", wordService.getAll());
        
        // "all_words"ビューを返します。
        return "all_words";
    }

    /**
     * 指定されたIDと名前に基づいて単語の詳細を表示するメソッド。
     * 
     * @param id URLパスから取得する単語のID。
     * @param name URLパスから取得する単語の名前。
     * @param model SpringのModelオブジェクト。ビューにデータを渡すために使用します。
     * @return 表示するビューの名前（"word_detail"）。
     */
    @GetMapping("/{id}/{name}")
    public String showDetail(
            @PathVariable("id") Integer id, // パス変数からIDを取得
            @PathVariable("name") String name, // パス変数から名前を取得（未使用）
            Model model) {
        
        // 指定されたIDに基づいて単語の詳細を取得し、モデルに追加
        model.addAttribute("word", wordService.getWordById(id));
        
        //モデル格納後、最終閲覧日を更新し、詳細ページからジャンプしたら最終閲覧日がアップロードされた風にする
        wordService.updateWordById(id);
        
        /** デバッグ用
        *System.out.println(id);
        *System.out.println(name);
        *System.out.println(wordService.getWordById(id));
        */
        // "word_detail"ビューを返します。
        return "word_detail";
    }
}
