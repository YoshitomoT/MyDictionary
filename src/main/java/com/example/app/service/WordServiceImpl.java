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

	@Override
	public List<Word> getAll() {
		return wordMapper.selectAll();
	}

	@Override
	public Word getWordById(Long wordId) {
		
		//wordIdに対応した単語情報の閲覧回数をインクリメント
		wordMapper.incrementPageViews(wordId);
		
		//WordIdに対応した単語情報を変数に格納
		Word word = wordMapper.selectWordById(wordId);
		
		return word;
	}



	@Override
	public void updateWordById(Long wordId) {
		//対象となる単語内、最終閲覧日を更新するメソッド
		wordMapper.updateLastViewById(wordId);
	}

}
