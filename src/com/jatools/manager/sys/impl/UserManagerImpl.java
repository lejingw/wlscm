package com.jatools.manager.sys.impl;

import java.util.List;

import com.jatools.dao.sys.UserDao;
import com.jatools.manager.sys.UserManager;
import com.jatools.vo.sys.User;

public class UserManagerImpl implements UserManager{
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * 获取用户信息
	 * @param usercode
	 * @return
	 */
	public User getUserInfo(String usercode){
		User user = userDao.getUserInfo(usercode);
		return user;
	}
	
	/**
	 * 获取所有用户信息
	 * @return
	 */
	public List<User> getAllUser(){
		return userDao.getAllUser();
	}
}
