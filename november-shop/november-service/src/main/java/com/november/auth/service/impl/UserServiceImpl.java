package com.november.auth.service.impl;

import com.noember.user.userException.UserException;
import com.november.auth.domain.User;
import com.november.auth.mapper.UserMapper;
import com.november.auth.service.UserService;
import com.november.no.Enum.TempEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserMapper userMapper;

	@Override
	public User findUserByName(String username) {
		User user = userMapper.findUserByName(username);
		
		return user;
	}

	@Override
	public String registerUser(User user) {
		if (Objects.isNull(user)){
			throw new UserException(TempEnum.PARAM_EXCEPTION);
		}
		int insert = userMapper.insert(user);

		if (insert !=1){
			throw new UserException(TempEnum.DATA_PUSH);
		}
		return "success";
	}
}
