package com.yishenxiao.commons.beans;

import java.io.Serializable;

public class UpdateArticleTypeData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long userId;
    private Long articleTypeId;
    private Integer type;//0 代表减，1代表加
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getArticleTypeId() {
		return articleTypeId;
	}
	public void setArticleTypeId(Long articleTypeId) {
		this.articleTypeId = articleTypeId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "UpdateArticleTypeData [userId=" + userId + ", articleTypeId=" + articleTypeId + ", type=" + type + "]";
	}
}
