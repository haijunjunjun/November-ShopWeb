package com.november.auth.mapper;

import com.november.auth.domain.User;
import com.november.mymapper.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends MyMapper<User>{
	
	/**
	 * 根据名称查询用户
	 * @param username
	 * @return
	 */
	User findUserByName(@Param("username")String username);

	User registerUser (User user);
}
