package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.mapper.DictWordMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DictWordServiceImpl implements DictWordService {
	
	private final DictWordMapper dictWordMapper;

	@Override
	public void setDictWord(Long editedWordId, List<Integer> editedDictIdList) {
		
		//dictionary_wordテーブルから該当するword_idの行を削除
		dictWordMapper.deleteDictWordByWordId(editedWordId);
		
		
		//新たに追加したい辞書情報をテーブルに追加する
		
		for (Integer editedDictId : editedDictIdList) {
			dictWordMapper.insertDictWordByWordIdAndDictId(editedWordId, editedDictId);
			
		}
	}



}
