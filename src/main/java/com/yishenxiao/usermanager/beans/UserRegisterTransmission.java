package com.yishenxiao.usermanager.beans;

import java.io.Serializable;

public class UserRegisterTransmission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String noNativeCodePhone;
	private String nativecode;
	private String invitationCode;
	public String getNoNativeCodePhone() {
		return noNativeCodePhone;
	}
	public void setNoNativeCodePhone(String noNativeCodePhone) {
		this.noNativeCodePhone = noNativeCodePhone;
	}
	public String getNativecode() {
		return nativecode;
	}
	public void setNativecode(String nativecode) {
		this.nativecode = nativecode;
	}
	public String getInvitationCode() {
		return invitationCode;
	}
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	@Override
	public String toString() {
		return "UserRegisterTransmission [noNativeCodePhone=" + noNativeCodePhone + ", nativecode=" + nativecode
				+ ", invitationCode=" + invitationCode + "]";
	}
}
