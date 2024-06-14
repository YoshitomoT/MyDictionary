package com.example.app.controller;

import org.springframework.stereotype.Controller;

/**
 * ルートURLからのリクエストをハンドリングし、/myshoko/show/all にリダイレクトするコントローラ。
 */
@Controller
public class RootController {

    /**
     * ルートURL (/) へのGETリクエストを /myshoko/show/all にリダイレクトします。
     * 
     * @return リダイレクト先のURL。
     */
    // 本番環境用
    // @GetMapping("/myshoko")
    
//    // 開発環境用
//    @GetMapping("/")
//    public String redirectTomyshokoShow() {
//        // /myshoko/show/all にリダイレクト
//        return "redirect:/myshoko/show/all";
//    }
}

