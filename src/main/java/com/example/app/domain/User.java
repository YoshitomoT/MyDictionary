package com.example.app.domain;

import lombok.Data;

@Data
public class User {
	//ユーザーID
	private Integer id;
	
	//ユーザー名
	private String userName;
	
	//パスワード
	private String hashedPassword;
	
	

}
