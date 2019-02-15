package com.yishenxiao.commons.beans;

import java.io.Serializable;

public class ClickRead implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long articleShareUserId;
	private String dataId;
	private String redisId;
	private Long userArticleId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getArticleShareUserId() {
		return articleShareUserId;
	}
	public void setArticleShareUserId(Long articleShareUserId) {
		this.articleShareUserId = articleShareUserId;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public String getRedisId() {
		return redisId;
	}
	public void setRedisId(String redisId) {
		this.redisId = redisId;
	}
	public Long getUserArticleId() {
		return userArticleId;
	}
	public void setUserArticleId(Long userArticleId) {
		this.userArticleId = userArticleId;
	}
	@Override
	public String toString() {
		return "ClickRead [userId=" + userId + ", articleShareUserId=" + articleShareUserId + ", dataId=" + dataId
				+ ", redisId=" + redisId + ", userArticleId=" + userArticleId + "]";
	}

}
