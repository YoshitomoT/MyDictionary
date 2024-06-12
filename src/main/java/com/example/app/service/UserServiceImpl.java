package com.example.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.User;
import com.example.app.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserMapper userMapper;

	@Override
	public boolean isCorrectUserNameAndPassword(String userName, String password) {
		
		System.out.println("**************************" + userName);
		System.out.println("**************************" + password);
		User user = userMapper.selectByUserName(userName);
		System.out.println("**************************" + user);
		
		if(user == null) {
			return false;
		}
		
//		if(!BCrypt.checkpw(password, user.getPassword())) {
//			return false;
//		} 
		if(!password.equals(user.getPassword())) {
			return false;
		} 
		
		
		
		return true;
	}

	@Override
	public Integer getIdByUser(User user) {
		user = userMapper.selectByUserName(user.getUserName());
		return user.getId();
	}

}
