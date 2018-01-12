package com.november.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.november.auth.domain.User;

/**
 * shiro工具类
 * @author yangzexu
 *
 */
public class ShiroUtils {
	/**
	 * 获取当前登陆用户
	 * @return
	 */
	public static User getCurrentUser(){
		return (User) getSubject().getPrincipal();
	}
	
	static public String getLoginName() {
		User user = getCurrentUser();
		return user != null ? user.getLoginName() : null;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	public static Integer getUserId(){
		return getCurrentUser().getId();
	}
	/**
	 * 登出
	 */
	public static void logout(){
		getSubject().logout();
	}
	
	/**
	 * 获取session
	 * @return
	 */
	static public Session getSession() {
		Session session = getSubject().getSession();
		return session;
	}
}