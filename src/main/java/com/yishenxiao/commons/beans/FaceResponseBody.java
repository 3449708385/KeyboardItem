package com.yishenxiao.commons.beans;

import java.io.Serializable;
import java.util.Map;

public class FaceResponseBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String address;
	private String config_str;
	private Map<String,Object> face_rect;
	private String name;
	private String nationality;
	private String num;
	private String sex;
	private String birth;
	private String request_id;
	private boolean success;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getConfig_str() {
		return config_str;
	}
	public void setConfig_str(String config_str) {
		this.config_str = config_str;
	}
	public Map<String, Object> getFace_rect() {
		return face_rect;
	}
	public void setFace_rect(Map<String, Object> face_rect) {
		this.face_rect = face_rect;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	@Override
	public String toString() {
		return "FaceResponseBody [address=" + address + ", config_str=" + config_str + ", face_rect=" + face_rect
				+ ", name=" + name + ", nationality=" + nationality + ", num=" + num + ", sex=" + sex + ", birth="
				+ birth + ", request_id=" + request_id + ", success=" + success + "]";
	}
}
