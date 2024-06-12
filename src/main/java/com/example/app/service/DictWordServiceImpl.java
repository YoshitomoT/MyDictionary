package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.domain.Dictionary;
import com.example.app.mapper.DictWordMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DictWordServiceImpl implements DictWordService {
	
	private final DictWordMapper dictWordMapper;
	
//	@Override
//	public List<Long> getWordId() {
//		return dictWordMapper.selectWordIdWithDictInfo();
//	}

	@Override
	public void setDictWord(Long editedWordId, List<Integer> editedDictIdList) {
		
		//dictionary_wordテーブルから該当するword_idの行を削除
		dictWordMapper.deleteDictWordByWordId(editedWordId);
		
		
		//新たに追加したい辞書情報をテーブルに追加する
		
		for (Integer editedDictId : editedDictIdList) {
			dictWordMapper.insertDictWordByWordIdAndDictId(editedWordId, editedDictId);
			
		}
	}

	@Override
	public void deleteDictWordById(Long WordId) {
		dictWordMapper.deleteDictWordByWordId(WordId);
		
	}

	@Override
	public void deleteDictWordByDictId(Integer dictId) {
		dictWordMapper.deleteDictWordByDictId(dictId);
		
	}

	@Override
	public List<Dictionary> getDictListByWordId(Long wordId) {
		
		return dictWordMapper.selectDictListByWordId(wordId);
	}







}
