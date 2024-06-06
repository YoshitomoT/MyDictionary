package com.example.app.service;


import java.util.List;

import com.example.app.domain.Dictionary;

public interface DictService {
	
	List<Dictionary> getAll();

	void registerDict(Dictionary addDict);

}
