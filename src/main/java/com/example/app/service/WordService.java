package com.example.app.service;

import java.util.List;

import com.example.app.domain.Word;

/**
 * WordServiceインターフェースは、単語の管理に関するサービスを提供します。
 * このインターフェースを実装するクラスは、単語の取得および管理に関する具体的なロジックを実装します。
 */
public interface WordService {
    
    /**
     * 登録されているすべての単語を取得するメソッド。
     * 
     * @return 単語のリスト。
     */
    List<Word> getAll();

    /**
     * 指定されたIDに基づいて単語を取得するメソッド。
     * 
     * @param wordId 取得したい単語のID。
     * @return 指定されたIDに対応する単語。存在しない場合はnullまたは例外をスローする可能性があります。
     */
    Word getWordById(int wordId);
    
    /**
     * 指定されたIDに基づいて単語の最終閲覧日を更新するメソッド。
     * 
     * @param wordId 取得したい単語のID。
     * 
     */

	void updateWordById(Integer id);
}
