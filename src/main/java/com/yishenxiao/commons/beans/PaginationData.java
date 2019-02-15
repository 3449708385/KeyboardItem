package com.yishenxiao.commons.beans;

import java.io.Serializable;

public class PaginationData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer limitCount;
	private Long id;
	private Integer startId;
	private Long userId;
	public Integer getLimitCount() {
		return limitCount;
	}
	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getStartId() {
		return startId;
	}
	public void setStartId(Integer startId) {
		this.startId = startId;
	}
	@Override
	public String toString() {
		return "PaginationData [limitCount=" + limitCount + ", id=" + id + ", startId=" + startId + ", userId=" + userId
				+ "]";
	}
	
}
