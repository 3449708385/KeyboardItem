package com.yishenxiao.usermanager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yishenxiao.usermanager.beans.UserShare;
import com.yishenxiao.usermanager.dao.UserShareMapper;
import com.yishenxiao.usermanager.service.UserShareService;

@Service("userShareService")
public class UserShareServiceImpl implements UserShareService {

	//private Logger logger = LoggerFactory.getLogger(UserScheduleServiceImpl.class);

	@Autowired(required = false)
	@Qualifier("userShareMapper")
	private UserShareMapper userShareMapper;
	
	@Override
	public List<UserShare> queryByUserIdAndDataId(Long userId, String dataId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("dataId", dataId);
		return userShareMapper.queryByUserIdAndDataId(map);
	}

	@Override
	public int insertOneData(UserShare userShare) {
		return userShareMapper.insert(userShare);
	}

	@Override
	public List<UserShare> queryByDataId(String dataId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dataId", dataId);
		return userShareMapper.queryByDataId(map);
	}

	@Override
	public List<UserShare> queryByUserId(Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		return userShareMapper.queryByUserId(map);
	}

}
