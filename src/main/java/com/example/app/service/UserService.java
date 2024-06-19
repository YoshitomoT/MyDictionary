package com.example.app.service;

import com.example.app.domain.User;
import com.example.app.dto.LoginFormDTO;
import com.example.app.dto.UserRegistrationDTO;
import com.example.app.dto.UserSessionDTO;

public interface UserService {
	
	void registerNewUser(UserRegistrationDTO userForm) throws Exception;
	
	boolean isUserNameUnique(String userName);
	
	Integer getIdByUser(User user);

	User createLoginUser(LoginFormDTO user);

	UserSessionDTO authenticateUser(LoginFormDTO loginForm);


}
