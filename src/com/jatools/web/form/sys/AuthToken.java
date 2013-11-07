package com.jatools.web.form.sys;

import java.io.Serializable;

/**
 * AuthToken 在用户登录后被创建,包含用户的id，名称
 * @author wanglj
 * 
 */
public class AuthToken implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 组织
	 */
	private String orgId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
