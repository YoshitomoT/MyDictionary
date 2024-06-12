package com.example.app.service;

import java.util.List;

import com.example.app.domain.Dictionary;

public interface DictWordService {
    
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


	List<Dictionary> getDictListByWordId(Long Word);
	
}

