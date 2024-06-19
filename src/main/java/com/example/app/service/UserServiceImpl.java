package com.example.app.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.User;
import com.example.app.dto.LoginFormDTO;
import com.example.app.dto.UserRegistrationDTO;
import com.example.app.dto.UserSessionDTO;
import com.example.app.exception.UserRegistrationException.PasswordsNotMatchingException;
import com.example.app.exception.UserRegistrationException.UserAlreadyExistsException;
import com.example.app.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserMapper userMapper;
	
	@Override
	public void registerNewUser(UserRegistrationDTO userForm) throws Exception {
		
        // パスワードの一致チェック
        if (!userForm.isPasswordMatching()) {
            throw new PasswordsNotMatchingException("パスワードと確認用パスワードが一致しません。");
        }
        
        // ユーザー名の重複チェック
        if (!isUserNameUnique(userForm.getUserName())) {
            throw new UserAlreadyExistsException("ユーザー名「 " + userForm.getUserName() + " 」は既に使用されています。別のユーザー名を指定してください。");
        }
        
		// ユーザーの登録処理
        User user = new User();
        user.setUserName(userForm.getUserName());
        user.setHashedPassword(BCrypt.hashpw(userForm.getPassword(), BCrypt.gensalt())); 

        // 登録処理を行う（例: ユーザーをデータベースに保存）
        userMapper.insertUser(user);
	}
	
	@Override
	public boolean isUserNameUnique(String userName) {
		User exsitingUser = userMapper.selectByUserName(userName);
		return exsitingUser ==null;
	}
	
	
	@Override
	public Integer getIdByUser(User user) {
		user = userMapper.selectByUserName(user.getUserName());
		return user.getId();
	}

	@Override
	public User createLoginUser(LoginFormDTO loginUser) {
		User user = userMapper.selectByUserName(loginUser.getUserName());
		user.setHashedPassword(null);
		System.out.println("保存されている情報：" + user);
		return user;
	}

	@Override
	public UserSessionDTO authenticateUser(LoginFormDTO loginForm) {
		
		System.out.println("入力された情報loginForm：" + loginForm);
		User user = userMapper.selectByUserName(loginForm.getUserName());
		System.out.println("保存されている情報user：" + user);
		
		if(user == null) {
			return null;
		}
		
		if(!BCrypt.checkpw(loginForm.getPassword(), user.getHashedPassword())) {
			System.out.println("認証NG");
			return null;
		} 
		
		System.out.println("認証OK");
        // 認証成功時に UserSessionDTO を作成して返す
        UserSessionDTO userSessionDTO = new UserSessionDTO();
        userSessionDTO.setUserId(user.getId());
        userSessionDTO.setUserName(user.getUserName());
		return userSessionDTO;

	}
}