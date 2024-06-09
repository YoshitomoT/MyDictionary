package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.domain.Dictionary;
import com.example.app.mapper.DictMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DictServiceImpl implements DictService {

	private final DictMapper dictMapper;

	@Override
	public List<Dictionary> getAll() {
		return dictMapper.selectAll();
	}

	@Override
	public void registerDict(Dictionary addDict) {
		
		//その他辞典を最後にする形でidをインクリメントしたいから現在のカラム数を新規登録する辞典のid番号にする
		int addId = dictMapper.countDict();
		addDict.setId(addId);
		dictMapper.insertDict(addDict);
		
	}

	@Override
	public Dictionary getDictById(Integer dictId) {
		return dictMapper.selectDictById(dictId);
	}

	@Override
	public void deleteDictById(Integer dictId) {
		dictMapper.deleteDict(dictId);
	}

}
