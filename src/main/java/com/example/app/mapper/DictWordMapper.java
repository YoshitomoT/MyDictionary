package com.example.app.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictWordMapper {

	
	void deleteDictWordByWordId(Long editedWordId);
	
	void insertDictWordByWordIdAndDictId(Long editedWordId, Integer editedDictId);

}
