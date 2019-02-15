package com.yishenxiao.usermanager.beans;

import java.io.Serializable;

public class SignUpBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;
	private UserSchedule userSchedule;
	private Boolean userLogin;
	private String accessToken;
	private String userInvitationCodeTotal;
	private String userInvitationCode;
	private int sureType;//0代表没有设置密码，1代表设置了密码
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserSchedule getUserSchedule() {
		return userSchedule;
	}
	public void setUserSchedule(UserSchedule userSchedule) {
		this.userSchedule = userSchedule;
	}
	public Boolean getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(Boolean userLogin) {
		this.userLogin = userLogin;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getUserInvitationCode() {
		return userInvitationCode;
	}
	public void setUserInvitationCode(String userInvitationCode) {
		this.userInvitationCode = userInvitationCode;
	}
	public String getUserInvitationCodeTotal() {
		return userInvitationCodeTotal;
	}
	public void setUserInvitationCodeTotal(String userInvitationCodeTotal) {
		this.userInvitationCodeTotal = userInvitationCodeTotal;
	}
	public int getSureType() {
		return sureType;
	}
	public void setSureType(int sureType) {
		this.sureType = sureType;
	}
	@Override
	public String toString() {
		return "SignUpBean [user=" + user + ", userSchedule=" + userSchedule + ", userLogin=" + userLogin
				+ ", accessToken=" + accessToken + ", userInvitationCodeTotal=" + userInvitationCodeTotal
				+ ", userInvitationCode=" + userInvitationCode + ", sureType=" + sureType + "]";
	} 
}
