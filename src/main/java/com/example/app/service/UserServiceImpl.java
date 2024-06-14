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
	public void registerNewUser(UserRegistrationDTO userForm) {
		// ユーザーの登録処理
        User user = new User();
        user.setUserName(userForm.getUserName());
        user.setHashedPassword(BCrypt.hashpw(userForm.getPassword(), BCrypt.gensalt())); 

        // 登録処理を行う（例: ユーザーをデータベースに保存）
        save(user);
	}
	
	@Override
	public void save(User user) {
		userMapper.insertUser(user);
	}

	@Override
	public Integer getIdByUser(User user) {
		user = userMapper.selectByUserName(user.getUserName());
		return user.getId();
	}

}
