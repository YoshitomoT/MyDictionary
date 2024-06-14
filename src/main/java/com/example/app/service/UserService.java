package com.example.app.service;

import com.example.app.domain.User;

public interface UserService {
	
	void registerNewUser(UserRegistrationDTO userForm);
	
	void save(User user);

	Integer getIdByUser(User user);

}
