package com.example.app.service;

import java.util.ArrayList;
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
	public List<Word> getAll() {
		
		List<Word> wordList = wordMapper.selectAll();
		List<Long> wordIdListWithDictInfo = dictWordService.getWordId();
//		System.out.println("テスト：wordList->" + wordList);
//		System.out.println("テスト：wordIdListWithDictInfo->" + wordIdListWithDictInfo);
		
		List<Integer> DictId = new ArrayList<>();
		DictId.add(0, 99);
		
		for (Word word : wordList) {
			if (!wordIdListWithDictInfo.contains(word.getId())) {
				dictWordService.setDictWord(word.getId(), DictId);
			}
		}
		
		return wordMapper.selectAllWithDict();
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
	public void setEditedWord(Word editedWord) {
		wordMapper.updateWordByEditedWord(editedWord);
	}

	@Override
	public void deleteWordById(Long wordId) {
		wordMapper.deleteWordById(wordId);
	}

	@Override
	public void setNewWord(Word addWord) {
		wordMapper.insertWord(addWord);
	}

	@Override
	public Long getLastInsertedId() {
		return wordMapper.selectLastInsertedId();
	}

	@Override
	public int getTotalWords() {
		return wordMapper.countTotalWords();
	}

	
		
		
		
		

}
