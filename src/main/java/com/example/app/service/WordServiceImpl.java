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
	public Word getWordById(int wordId) {
		
		return wordMapper.selectWordById(wordId);
	}

}
