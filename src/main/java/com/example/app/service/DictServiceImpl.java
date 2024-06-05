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
		dictMapper.insertDict(addDict);
		
	}

}
