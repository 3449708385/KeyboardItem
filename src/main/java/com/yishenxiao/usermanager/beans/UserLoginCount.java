package com.yishenxiao.usermanager.beans;

import java.io.Serializable;

public class UserLoginCount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UserLoginCount [userId=" + userId + "]";
	}
}
