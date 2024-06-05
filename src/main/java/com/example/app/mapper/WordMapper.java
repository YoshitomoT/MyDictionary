package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Word;

@Mapper
public interface WordMapper {
	
	List<Word> selectAll();

	Word selectWordById(Long wordId);

	Integer incrementPageViews(Long wordId);

	void updateWordLastViewedById(Long wordId);

	void updateWordByEditedWord(Word editedWord);

	void deleteWordById(Long wordId);
	
	void insertWord(Word addWord);

}
