package com.yishenxiao.usermanager.service;

import java.util.List;

import com.yishenxiao.usermanager.beans.UserArticle;

public interface UserArticleService {

	int insertOneData(UserArticle userArticle);
    /**
     * @info 更改推广数据的状态
     * @param id
     * @param state
     * @return
     */
	int updateArticleState(Long id, int state);
    /**
     * @info 根据id查询推广数据信息
     * @param userArticleId
     * @return
     */
	List<UserArticle> queryById(Long userArticleId);
    /**
     * @info 更改推广数据的状态，重置剩余的mol数
     * @param userArticleId
     * @param state
     * @param molCount
     * @return
     */
	int updateArticleStateAndExtensionMonery(Long userArticleId, int state, Double molCount);
	/**
     * @info 根据userId查询UserArticle List
     * @param userArticleId
     * @param state
     * @param molCount
     * @return
     */
	List<UserArticle> queryByUserId(Long userId);
	/**
     * @info 根据userId查询UserArticle List 并进行分页
     * @param userArticleId
     * @param state
     * @param molCount
     * @return
     */
	List<UserArticle> queryByUserIdAndPagination(Long userId, Long id, Integer limitCount);
	
	 /**
     * @info 更改推广数据的状态，增加mol数，修改type为0
     * @param userArticleId
     * @param state
     * @param molCount
     * @return
     */
	int updateArticleStateAndExtensionMoneryAndType(Long userArticleId, int state, Double molCount);
	 /**
     * @info 减少mol数
     * @param userArticleId
     * @param state
     * @param molCount
     * @return
     */
	int updateArticleExtensionMonery(Long dataId, Double molCount);
	 /**
     * @param clickCount 
	 * @info 减少mol数
     * @param userArticleId
     * @param state
     * @param molCount
     * @return
     */
	int updateArticleExtensionMoneryAndClickurlcount(Long dataId, Double dataCount, int clickCount);
	 /**
	 * @info 根据mongoId查询数据
     * @return
     */
	List<UserArticle> queryByArticlemongoid(String mongoId);
	/**
	 * @info 根据根据文章分类查询多条数据,客户端返回数据专用
     * @return
     */
	List<UserArticle> queryByMolAndLimit(String articleType, Integer limitCount, Double molCount, Long userId);

}
