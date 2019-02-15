package com.yishenxiao.usermanager.beans;

import java.io.Serializable;

public class UserPhoneCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String noNativeCodePhone;
    private String nativecode;
    private Integer type;
    private String phoneCode;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPhoneCode() {
		return phoneCode;
	}
	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
	@Override
	public String toString() {
		return "UserPhoneCode [noNativeCodePhone=" + noNativeCodePhone + ", nativecode=" + nativecode + ", type=" + type
				+ ", phoneCode=" + phoneCode + "]";
	}
    
}
