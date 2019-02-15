package com.yishenxiao.commons.beans;

import java.io.Serializable;
import java.util.List;

public class ClientUpdateData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String context;
	private String createTime;
	private int readCount;
	private int forward;
	private String moldata;
	private Integer state;	//	0代表正常，1代表取消推广，2代表该条数据已经废弃
	private Long id;
	private Long articleShareUserId;
	private Long userId;
	private String networkUrl;
	private List<String> pictureList;
	private String nickName;
	private String headIcon;
	private String phone; 
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getForward() {
		return forward;
	}
	public void setForward(int forward) {
		this.forward = forward;
	}
	public String getMoldata() {
		return moldata;
	}
	public void setMoldata(String moldata) {
		this.moldata = moldata;
	}
	public List<String> getPictureList() {
		return pictureList;
	}
	public void setPictureList(List<String> pictureList) {
		this.pictureList = pictureList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getArticleShareUserId() {
		return articleShareUserId;
	}
	public void setArticleShareUserId(Long articleShareUserId) {
		this.articleShareUserId = articleShareUserId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getNetworkUrl() {
		return networkUrl;
	}
	public void setNetworkUrl(String networkUrl) {
		this.networkUrl = networkUrl;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadIcon() {
		return headIcon;
	}
	public void setHeadIcon(String headIcon) {
		this.headIcon = headIcon;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "ClientUpdateData [context=" + context + ", createTime=" + createTime + ", readCount=" + readCount
				+ ", forward=" + forward + ", moldata=" + moldata + ", state=" + state + ", id=" + id
				+ ", articleShareUserId=" + articleShareUserId + ", userId=" + userId + ", networkUrl=" + networkUrl
				+ ", pictureList=" + pictureList + ", nickName=" + nickName + ", headIcon=" + headIcon + ", phone="
				+ phone + "]";
	}
}
