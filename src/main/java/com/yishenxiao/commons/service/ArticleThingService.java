package com.yishenxiao.commons.service;

import java.util.List;

import com.yishenxiao.commons.beans.ClickRead;
import com.yishenxiao.commons.beans.ClientUpdateData;
import com.yishenxiao.commons.beans.IdentityRealNameAuthentication;
import com.yishenxiao.commons.beans.PaginationData;
import com.yishenxiao.commons.beans.PublishArticle;

public interface ArticleThingService {

	/**
	 * @info 发布广告
	 * @param publishArticle
	 * @return
	 */
	int userReleaseArticle(PublishArticle publishArticle);

	/**
	 * @info 取消推广
	 * @param publishArticle
	 * @return
	 */
	int userCancelRelease(PublishArticle publishArticle);
    /**
     * @info 得到公告list
     * @param paginationData
     * @return
     */
	List<ClientUpdateData> clientUpdateData(PaginationData paginationData);

	/**
	 * @info 给该广告充值
	 * @param id
	 * @param double1
	 * @return
	 */
	int advertisingRecharge(Long id, Double molCount);
	
	/**
	 * @param identityRealNameAuthentication 
	 * @info 用户实名认证
	 * @param id
	 * @param double1
	 * @return
	 */
	int identityRealNameAuthentication(IdentityRealNameAuthentication identityRealNameAuthentication);

	/**
	 * @param  
	 * @info 客户端点击处理
	 * @param id
	 * @param double1
	 * @return
	 */
	int clickReadData(ClickRead clickRead);
	
	  /**
     * @info 得到用户的公告list
     * @param paginationData
     * @return
     */
	List<ClientUpdateData> userSelfRelease(PaginationData paginationData);

}
