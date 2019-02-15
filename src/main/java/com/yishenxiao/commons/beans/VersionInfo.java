package com.yishenxiao.commons.beans;

import java.io.Serializable;

public class VersionInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String versionNum;
	private Integer type;//0代表安卓，1代表ios
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}
	@Override
	public String toString() {
		return "VersionInfo [userId=" + userId + ", versionNum=" + versionNum + ", type=" + type + "]";
	}
}
