package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Word;

@Mapper
public interface WordMapper {
	
	List<Word> selectAll();

	Word selectWordById(int wordId);

	Integer incrementPageViews(int wordId);

}
