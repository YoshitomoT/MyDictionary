package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.Dictionary;

@Mapper
public interface DictMapper {

	List<Dictionary> selectAll(Integer userId);

	void insertDict(@Param("userId")Integer userId, @Param("dict") Dictionary dict);


	int countDict();

	Dictionary selectDictById(Integer dictId);

	void deleteDict(Integer dictId);

	void updateDict(Dictionary dict);
	
	

}
