package com.hyzs.cidyth.portal.controller.user;

public class LoginInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4539755225608254740L;
	private String account;// 登录账户
	private String password;// 登录密码

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
