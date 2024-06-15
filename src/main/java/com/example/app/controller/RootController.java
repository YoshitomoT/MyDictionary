package com.example.app.controller;

import org.springframework.stereotype.Controller;

/**
 * ルートURLからのリクエストをハンドリングし、/mydictionary/show/all にリダイレクトするコントローラ。
 */
@Controller
public class RootController {

    /**
     * ルートURL (/) へのGETリクエストを /mydictionary/show/all にリダイレクトします。
     * 
     * @return リダイレクト先のURL。
     */
    // 本番環境用
    // @GetMapping("/mydictionary")
    
//    // 開発環境用
//    @GetMapping("/")
//    public String redirectToMyDictionaryShow() {
//        // /mydictionary/show/all にリダイレクト
//        return "redirect:/mydictionary/show/all";
//    }
}

