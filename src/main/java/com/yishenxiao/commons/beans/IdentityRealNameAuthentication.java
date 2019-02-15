package com.yishenxiao.commons.beans;

import java.io.Serializable;
import java.util.List;

public class IdentityRealNameAuthentication implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String realName;
	private String identityNum;
	private List<String> identityPicture;
	private Long userId;
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdentityNum() {
		return identityNum;
	}
	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}
	public List<String> getIdentityPicture() {
		return identityPicture;
	}
	public void setIdentityPicture(List<String> identityPicture) {
		this.identityPicture = identityPicture;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "IdentityRealNameAuthentication [realName=" + realName + ", identityNum=" + identityNum
				+ ", identityPicture=" + identityPicture + ", userId=" + userId + "]";
	}
    
}
