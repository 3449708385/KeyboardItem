package com.yishenxiao.usermanager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yishenxiao.usermanager.beans.UserArticleMol;
import com.yishenxiao.usermanager.dao.UserArticleMolMapper;
import com.yishenxiao.usermanager.service.UserArticleMolService;

@Service("userArticleMolService")
public class UserArticleMolServiceImpl implements UserArticleMolService {

	@Autowired(required = false)
	@Qualifier("userArticleMolMapper")
	private UserArticleMolMapper userArticleMolMapper;
	
	@Override
	public List<UserArticleMol> queryByUserId(Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		return userArticleMolMapper.queryByUserId(map);
	}

	@Override
	public int insertOneData(UserArticleMol userArticleMol) {
		return userArticleMolMapper.insert(userArticleMol);
	}
	
	@Override
	public int updateTotalAndToday(Long id,Double dataCount){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dataCount", dataCount);
		map.put("id", id);
		return userArticleMolMapper.updateTotalAndToday(map);
	}

	@Override
	public int queryByAllDataCount() {
		return userArticleMolMapper.queryByAllDataCount();
	}

	@Override
	public List<UserArticleMol> queryPartData(int start, int eventCounts){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", start);
		map.put("eventCounts", eventCounts);
		return userArticleMolMapper.queryPartData(map);
	}

	@Override
	public int updateTodayMolCount(Long id, Double molCount) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("molCount", molCount);
		return userArticleMolMapper.updateTodayMolCount(map);
	}
}
