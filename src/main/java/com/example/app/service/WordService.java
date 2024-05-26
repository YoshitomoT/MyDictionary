package com.example.app.service;

import java.util.List;

import com.example.app.domain.Word;

public interface WordService {
	
	//登録した単語の一覧を取得するためのメソッド
	List<Word> getAll();

}
