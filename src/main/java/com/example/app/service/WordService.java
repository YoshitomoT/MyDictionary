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
    Word getWordById(Long wordId);
    
    
    /**
     * 指定されたIDに基づいて単語の閲覧回数を更新するメソッド。
     * 
     * @param id 更新したい単語のID。
     * 
     */
    void setPageViewsForWordById(Long wordId);
   
    /**
     * 指定されたIDに基づいて単語の最終閲覧日を更新するメソッド。
     * 
     * @param id 更新したい単語のID。
     * 
     */
	void setLastViewedDateForWordById(Long wordId);
	
    /**
     * 編集されたWord情報を受け取り単語情報を更新するメソッド。
     *
     * @param editedWord 更新したい単語のID+更新したい単語情報。
     * 
     */
	void setEditedWord(Word editedWord);

	void deleteWordById(Long WordId);
	
	
    /**
     * 新規単語登録の際、入力項目の説明をWordに入れるメソッド。
     *
     * @param addWord 更新したい単語のID+更新したい単語情報。
     * 
     */
	void setNewWord(Word addWord);
	
    /**
     * 新規単語登録の際、BDに最後に追加した単語のid情報を取得するメソッド。
     *
     * @return id BDに最後に追加した単語のid情報
     * 
     */
	Long getLastInsertedId();
	
    /**
     * 登録している全単語数を取得するメソッド。
     *
     * @return 登録している全単語数
     * 
     */
	int getTotalWords();
	
	

	
	
}
