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
	public List<Dictionary> getAll(Integer userId) {
			List<Dictionary> dictList = dictMapper.selectAll(userId);
			dictList.add(dictMapper.selectDictById(99));
		return dictList;
	}

	@Override
	public void registerDict(Integer userId, Dictionary dict) {
		
		//その他辞典を最後にする形でidをインクリメントしたいから現在のカラム数を新規登録する辞典のid番号にする
		int addId = dictMapper.countDict();
		dict.setId(addId);
		dictMapper.insertDict(userId, dict);
		
	}

	@Override
	public Dictionary getDictById(Integer dictId) {
		return dictMapper.selectDictById(dictId);
	}

	@Override
	public void deleteDictById(Integer dictId) {
		dictMapper.deleteDict(dictId);
	}

	@Override
	public void setDictByDict(Dictionary dict) {
		dictMapper.updateDict(dict);
	}

}
