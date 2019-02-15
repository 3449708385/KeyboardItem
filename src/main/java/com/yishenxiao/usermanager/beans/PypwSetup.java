package com.yishenxiao.usermanager.beans;

import java.io.Serializable;

public class PypwSetup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long userId;
    private String pypasswd;
    private String oldpypasswd;
    private String identificationCard;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPypasswd() {
		return pypasswd;
	}
	public void setPypasswd(String pypasswd) {
		this.pypasswd = pypasswd;
	}
	public String getOldpypasswd() {
		return oldpypasswd;
	}
	public void setOldpypasswd(String oldpypasswd) {
		this.oldpypasswd = oldpypasswd;
	}
	public String getIdentificationCard() {
		return identificationCard;
	}
	public void setIdentificationCard(String identificationCard) {
		this.identificationCard = identificationCard;
	}
	@Override
	public String toString() {
		return "PypwSetup [userId=" + userId + ", pypasswd=" + pypasswd + ", oldpypasswd=" + oldpypasswd
				+ ", identificationCard=" + identificationCard + "]";
	}
}
