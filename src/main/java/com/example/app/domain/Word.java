package com.example.app.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;


@Data
public class Word {
	
	private Integer id;
	
	//登録している辞典名
	private List<Dictionary> registeredDictList;
	
	//単語名
	private String name;
	
	//読み方
	private String pronunciation;
	
	//簡単な説明
	private String simpleDescription;
	
	//詳細な説明
	private String detailedDescription;
	
	//登録日時
	private LocalDateTime registeredAt;
	
	//更新日
	private LocalDateTime updatedAt;
	
	//最終閲覧日
	private LocalDateTime lastViewAt;
	
	//閲覧回数
	private Integer pageViews;
	

}
