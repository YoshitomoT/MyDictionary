package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Dictionary;

@Mapper
public interface DictWordMapper {

//	List<Long> selectWordIdWithDictInfo();
	List<Dictionary> selectDictListByWordId(Long wordId);
	
	void deleteDictWordByWordId(Long editedWordId);
	
	void insertDictWordByWordIdAndDictId(Long editedWordId, Integer editedDictId);

	void deleteDictWordByDictId(Integer dictId);




}
