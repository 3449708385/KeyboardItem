package com.yishenxiao.commons.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yishenxiao.commons.beans.MolHistoricalRecord;
import com.yishenxiao.commons.dao.MolHistoricalRecordMapper;
import com.yishenxiao.commons.service.MolHistoricalRecordService;

@Service("molHistoricalRecordService")
public class MolHistoricalRecordServiceImpl implements MolHistoricalRecordService {

	@Autowired(required = false)
	@Qualifier("molHistoricalRecordMapper")
	private MolHistoricalRecordMapper molHistoricalRecordMapper;

	@Override
	public int insertOneData(MolHistoricalRecord molHistoricalRecord) {
		return molHistoricalRecordMapper.insert(molHistoricalRecord);
	}

	@Override
	public List<MolHistoricalRecord> queryByUserId(Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		return molHistoricalRecordMapper.queryByUserId(map);
	}
	
}
