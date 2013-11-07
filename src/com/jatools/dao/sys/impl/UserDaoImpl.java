package com.jatools.dao.sys.impl;

import java.util.List;

import com.jatools.dao.BaseDao;
import com.jatools.dao.sys.UserDao;
import com.jatools.vo.sys.User;

public class UserDaoImpl extends BaseDao implements UserDao{
	public User getUserInfo(String usercode){
			User user = (User) executeQueryForObject("User.getUserInfo", usercode);
			return user;
	}

	/**
	 * 获取所有用户信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUser(){
		return (List<User>)executeQueryForList("User.getAllUser", null);
	}
}
