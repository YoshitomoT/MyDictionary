package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.domain.Word;
import com.example.app.mapper.WordMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

	private final WordMapper wordMapper;
	private final DictWordService dictWordService;
	

	@Override
	public List<Word> getAll(Integer userId) {
		
	    // 単語のリストを取得
	    List<Word> wordList = wordMapper.selectAll(userId);

	    // 各単語に対応する辞書情報を付加
	    for (Word word : wordList) {
	        word.setRegisteredDictList(dictWordService.getDictListByWordId(word.getId()));
	    }

	    return wordList;
	}

	@Override
	public Word getWordById(Long wordId) {
		//WordIdに対応した単語情報を変数に格納
		Word word = wordMapper.selectWordById(wordId);
		return word;
	}


	@Override
	public void setPageViewsForWordById(Long wordId) {
		//wordIdに対応した単語情報の閲覧回数をインクリメント
		wordMapper.incrementPageViews(wordId);
		
	}

	@Override
	//対象となる単語の最終閲覧日を更新するメソッド
	public void setLastViewedDateForWordById(Long wordId) {
		wordMapper.updateWordLastViewedById(wordId);
	}
	
	
	@Override
	public void setEditedWord(Word word) {
		wordMapper.updateWordByEditedWord(word);
	}

	@Override
	public void deleteWordById(Long wordId) {
		wordMapper.deleteWordById(wordId);
	}

	@Override
	public void setNewWord(Word word) {
		wordMapper.insertWord(word);
	}

	@Override
	public Long getLastInsertedId() {
		return wordMapper.selectLastInsertedId();
	}

	@Override
	public int getTotalWords(Integer userId) {
		return wordMapper.countTotalWords(userId);
	}

	
		
		
		
		

}
