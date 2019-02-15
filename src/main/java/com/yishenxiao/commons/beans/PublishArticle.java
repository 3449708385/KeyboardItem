package com.yishenxiao.commons.beans;

import java.io.Serializable;
import java.util.List;

public class PublishArticle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String articleContext;
	private List<String> pictureList;
    private Double rewardMolCount;
    private String articType;
    private Long userArticleId;
    private Long userId;
    private Integer type;//1代表取消推广，2代表删除
    private String pwpass;
	public String getArticleContext() {
		return articleContext;
	}
	public void setArticleContext(String articleContext) {
		this.articleContext = articleContext;
	}
	public List<String> getPictureList() {
		return pictureList;
	}
	public void setPictureList(List<String> pictureList) {
		this.pictureList = pictureList;
	}
	public Double getRewardMolCount() {
		return rewardMolCount;
	}
	public void setRewardMolCount(Double rewardMolCount) {
		this.rewardMolCount = rewardMolCount;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getArticType() {
		return articType;
	}
	public void setArticType(String articType) {
		this.articType = articType;
	}
	public Long getUserArticleId() {
		return userArticleId;
	}
	public void setUserArticleId(Long userArticleId) {
		this.userArticleId = userArticleId;
	}
	public String getPwpass() {
		return pwpass;
	}
	public void setPwpass(String pwpass) {
		this.pwpass = pwpass;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "PublishArticle [articleContext=" + articleContext + ", pictureList=" + pictureList + ", rewardMolCount="
				+ rewardMolCount + ", articType=" + articType + ", userArticleId=" + userArticleId + ", userId="
				+ userId + ", type=" + type + ", pwpass=" + pwpass + "]";
	}  
}
