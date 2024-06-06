package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictWordMapper {

	List<Long> selectWordIdWithDictInfo();
	
	void deleteDictWordByWordId(Long editedWordId);
	
	void insertDictWordByWordIdAndDictId(Long editedWordId, Integer editedDictId);


}
