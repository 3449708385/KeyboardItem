package com.yishenxiao.commons.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yishenxiao.commons.beans.PhoneSMS;
import com.yishenxiao.commons.dao.PhoneSMSMapper;
import com.yishenxiao.commons.service.PhoneSMSService;

@Service("phoneSMSService")
public class PhoneSMSServiceImpl implements PhoneSMSService {

	@Autowired(required = false)
	@Qualifier("phoneSMSMapper")
	private PhoneSMSMapper phoneSMSMapper;
	
	@Override
	public List<PhoneSMS> queryByPhone(String phone) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phone", phone);
		return phoneSMSMapper.queryByPhone(map);
	}

	@Override
	public int updateByCountAndData(PhoneSMS phoneSMS) {
		return phoneSMSMapper.updateByCountAndData(phoneSMS);
	}

	@Override
	public int insertData(PhoneSMS phoneSMS) {
		return phoneSMSMapper.insert(phoneSMS);
	}

	@Override
	public int queryByAllDataCount() {
		return phoneSMSMapper.queryByAllDataCount();
	}

	@Override
	public List<PhoneSMS> queryPartData(int startId, int eventCounts) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startId", startId);
		map.put("eventCounts", eventCounts);
		return phoneSMSMapper.queryPartData(map);
	}

	@Override
	public int unLockPhoneSMS(Long id, int count) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("count", count);
		return phoneSMSMapper.unLockPhoneSMS(map);
	}

}
