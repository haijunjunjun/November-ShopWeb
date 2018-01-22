package com.november.auth.service;

import com.november.auth.domain.User;

/**
 * 用户查询接口
 * @author yangzexu
 *
 */
public interface UserService {

	/**
	 * 根据名称查询用户
	 * @param username
	 * @return
	 */
	User findUserByName(String username);

	/**
	 * 根据用户信息进行注册
	 * @param user
	 * @return
	 * */
	String registerUser (User user);
}
