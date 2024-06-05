package com.example.app.domain;

import lombok.Data;

@Data
public class Dictionary {
	
	//辞典ID
	private Integer id;
	
	//辞典名
	private String name;
	
	//登録単語数
	private Long wordAmount;
	

}
