package com.example.app.service;


import java.util.List;

import com.example.app.domain.Dictionary;

public interface DictService {
	
	List<Dictionary> getAll(Integer userId);

	void registerDict(Dictionary addDict);

	Dictionary getDictById(Integer dictId);

	void deleteDictById(Integer dictId);

	void setDictByDict(Dictionary dict);

}
