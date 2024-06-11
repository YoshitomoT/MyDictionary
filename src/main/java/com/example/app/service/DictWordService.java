package com.example.app.service;

import java.util.List;

public interface DictWordService {
    
	/**
	 * 現在の辞典登録している単語のIDを取得するメソッド。
	 * 
	 * @return 辞典登録をしている単語IDのリスト
	 * 
	 */
	List<Long> getWordId();
	
	
    /**
     * 単語の辞典登録情報を更新するメソッド。
     * 
     * @param editedWordId 更新する単語のID
     * @param editedDictIdList 更新する単語が持つ登録辞書のIDリスト
     * 
     */
    void setDictWord(Long editedWordId, List<Integer> editedDictIdList);
    
    /**
     * 単語の辞典登録情報を削除するメソッド。
     * 
     * @param editedWordId 更新する単語のID
     * @param editedDictIdList 更新する単語が持つ登録辞書のIDリスト
     * 
     */
	void deleteDictWordById(Long WordId);

	void deleteDictWordByDictId(Integer dictId);
	
}

