package com.yishenxiao.usermanager.dao;

import java.util.List;
import java.util.Map;

import com.yishenxiao.usermanager.beans.UserArticle;

public interface UserArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserArticle record);

    int insertSelective(UserArticle record);

    UserArticle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserArticle record);

    int updateByPrimaryKey(UserArticle record);

	int updateArticleState(Map<String, Object> map);

	List<UserArticle> queryById(Map<String, Object> map);

	int updateArticleStateAndExtensionMonery(Map<String, Object> map);

	List<UserArticle> queryByUserId(Map<String, Object> map);

	List<UserArticle> queryByUserIdAndPagination(Map<String, Object> map);

	int updateArticleStateAndExtensionMoneryAndType(Map<String, Object> map);

	int updateArticleExtensionMonery(Map<String, Object> map);

	int updateArticleExtensionMoneryAndClickurlcount(Map<String, Object> map);

	List<UserArticle> queryByArticlemongoid(Map<String, Object> map);

	List<UserArticle> queryByMolAndLimit(Map<String, Object> map);
}