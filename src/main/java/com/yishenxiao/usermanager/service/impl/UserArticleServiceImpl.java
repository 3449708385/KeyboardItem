package com.yishenxiao.usermanager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yishenxiao.usermanager.beans.UserArticle;
import com.yishenxiao.usermanager.dao.UserArticleMapper;
import com.yishenxiao.usermanager.service.UserArticleService;

@Service("userArticleService")
public class UserArticleServiceImpl implements UserArticleService {
	
	@Autowired(required = false)
	@Qualifier("userArticleMapper")
	private UserArticleMapper userArticleMapper;

	@Override
	public int insertOneData(UserArticle userArticle) {
		return userArticleMapper.insert(userArticle);
	}

	@Override
	public int updateArticleState(Long id,int state) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("state", state);
		return userArticleMapper.updateArticleState(map);
	}

	@Override
	public List<UserArticle> queryById(Long userArticleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", userArticleId);
		return userArticleMapper.queryById(map);
	}

	@Override
	public int updateArticleStateAndExtensionMonery(Long userArticleId, int state, Double molCount) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", userArticleId);
		map.put("state", state);
		map.put("molCount", molCount);
		return userArticleMapper.updateArticleStateAndExtensionMonery(map);
	}

	@Override
	public List<UserArticle> queryByUserId(Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		return userArticleMapper.queryByUserId(map);
	}

	@Override
	public List<UserArticle> queryByUserIdAndPagination(Long userId, Long id, Integer limitCount) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("userId", userId);
		map.put("limitCount", limitCount);
		return userArticleMapper.queryByUserIdAndPagination(map);
	}

	@Override
	public int updateArticleStateAndExtensionMoneryAndType(Long userArticleId, int state, Double molCount) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", userArticleId);
		map.put("state", state);
		map.put("molCount", molCount);
		return userArticleMapper.updateArticleStateAndExtensionMoneryAndType(map);
	}

	@Override
	public int updateArticleExtensionMonery(Long dataId, Double molCount) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", dataId);
		map.put("molCount", molCount);
		return userArticleMapper.updateArticleExtensionMonery(map);
	}

	@Override
	public int updateArticleExtensionMoneryAndClickurlcount(Long dataId, Double dataCount, int clickCount) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", dataId);
		map.put("clickurlcount", clickCount);
		map.put("molCount", dataCount);
		return userArticleMapper.updateArticleExtensionMoneryAndClickurlcount(map);
	}

	@Override
	public List<UserArticle> queryByArticlemongoid(String mongoId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mongoId", mongoId);
		return userArticleMapper.queryByArticlemongoid(map);
	}

	@Override
	public List<UserArticle> queryByMolAndLimit(String articleType, Integer limitCount, Double molCount, Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("articleType", articleType);
		map.put("limitCount", limitCount);
		map.put("molCount", molCount);
		map.put("userId", userId);
		return userArticleMapper.queryByMolAndLimit(map);
	}

}
