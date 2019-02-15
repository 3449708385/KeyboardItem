package com.yishenxiao.usermanager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yishenxiao.usermanager.beans.UserIdentification;
import com.yishenxiao.usermanager.dao.UserIdentificationMapper;
import com.yishenxiao.usermanager.service.UserIdentificationService;

@Service("userIdentificationService")
public class UserIdentificationServiceImpl implements UserIdentificationService {

	@Autowired(required = false)
	@Qualifier("userIdentificationMapper")
	private UserIdentificationMapper userIdentificationMapper;
	
	@Override
	public List<UserIdentification> queryByUserId(Long userId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return userIdentificationMapper.queryByUserId(map);
	}

	@Override
	public int insertOneData(UserIdentification userIdentification) {
		return userIdentificationMapper.insert(userIdentification);
	}

	@Override
	public List<UserIdentification> queryByUserIdAndIdentification(Long userId, String identificationCard) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("identificationCard", identificationCard);
		return userIdentificationMapper.queryByUserIdAndIdentification(map);
	}

}
