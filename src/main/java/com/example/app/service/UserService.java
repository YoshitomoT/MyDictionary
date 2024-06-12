package com.example.app.service;

import com.example.app.domain.User;

public interface UserService {
	
	boolean isCorrectUserNameAndPassword(String userName, String password);

	Integer getIdByUser(User user);

}
