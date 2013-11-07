package com.jatools.manager.sys;

import java.util.List;

import com.jatools.vo.sys.User;

public interface UserManager {

	/**
	 * 获取用户信息
	 * 
	 * @param usercode
	 * @return
	 */
	public User getUserInfo(String usercode);
	/**
	 * 获取所有用户信息
	 * @return
	 */
	public List<User> getAllUser();
}
