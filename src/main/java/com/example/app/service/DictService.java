package com.example.app.service;


import java.util.List;

import com.example.app.domain.Dictionary;

public interface DictService {
	
	List<Dictionary> getAll(Integer userId);

	void registerDict(Integer userId, Dictionary dict);

	Dictionary getDictById(Integer dictId);

	void deleteDictById(Integer dictId);

	void setDictByDict(Dictionary dict);
	
	List<Dictionary> setDefaultDict(List<Dictionary> dictList, Integer userId, Integer dictId);

}
