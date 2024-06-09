package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Dictionary;

@Mapper
public interface DictMapper {

	List<Dictionary> selectAll();

	void insertDict(Dictionary addDict);

	int countDict();

	Dictionary selectDictById(Integer dictId);

	void deleteDict(Integer dictId);

	void updateDict(Dictionary dict);
	
	

}
