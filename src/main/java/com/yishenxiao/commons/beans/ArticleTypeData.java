package com.yishenxiao.commons.beans;

import java.io.Serializable;

public class ArticleTypeData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long id;
	private Double molCount;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getMolCount() {
		return molCount;
	}
	public void setMolCount(Double molCount) {
		this.molCount = molCount;
	}
	@Override
	public String toString() {
		return "ArticleTypeData [userId=" + userId + ", id=" + id + ", molCount=" + molCount + "]";
	}
}
