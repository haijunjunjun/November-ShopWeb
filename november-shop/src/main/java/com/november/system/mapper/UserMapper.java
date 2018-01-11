package com.november.system.mapper;

import org.apache.ibatis.annotations.Param;

import com.november.system.domain.User;

public interface UserMapper {
	
	/**
	 * 根据名称查询用户
	 * @param username
	 * @return
	 */
	public User findUserByName(@Param("username")String username);
}
