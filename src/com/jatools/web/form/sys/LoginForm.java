package com.jatools.web.form.sys;

import java.io.Serializable;

import com.jatools.web.form.BaseForm;

/**
 * 用户登陆信息对象
 *
 */
//public class LoginForm extends BaseForm implements Serializable {
public class LoginForm extends BaseForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -579415427600835972L;

	/**
	 * 客户登陆账户
	 */
	private String username; 

	/**
	 * 客户登陆密码
	 */
	private String password;
	
	/**
	 * 用户登陆IP
	 */
	private String userip;

	/**
	 * 用户登陆的MAC地址
	 */
	private String mac;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserip() {
		return userip;
	}

	public void setUserip(String userip) {
		this.userip = userip;
	}
}
