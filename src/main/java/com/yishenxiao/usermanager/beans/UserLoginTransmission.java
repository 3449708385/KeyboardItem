package com.yishenxiao.usermanager.beans;

import java.io.Serializable;

public class UserLoginTransmission implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String noNativeCodePhone;
	private String nativecode;
	private String invitationCode;
	private String phoneCode;
	private String deviceIdentificationCode;
	public String getDeviceIdentificationCode() {
		return deviceIdentificationCode;
	}
	public void setDeviceIdentificationCode(String deviceIdentificationCode) {
		this.deviceIdentificationCode = deviceIdentificationCode;
	}
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
	public String getPhoneCode() {
		return phoneCode;
	}
	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
	@Override
	public String toString() {
		return "UserLoginTransmission [noNativeCodePhone=" + noNativeCodePhone + ", nativecode=" + nativecode
				+ ", invitationCode=" + invitationCode + ", phoneCode=" + phoneCode + ", deviceIdentificationCode="
				+ deviceIdentificationCode  + "]";
	}
}
