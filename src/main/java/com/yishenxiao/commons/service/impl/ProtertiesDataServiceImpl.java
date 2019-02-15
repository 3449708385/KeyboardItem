package com.yishenxiao.commons.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yishenxiao.commons.beans.ProtertiesData;
import com.yishenxiao.commons.dao.ProtertiesDataMapper;
import com.yishenxiao.commons.service.ProtertiesDataService;

@Service("protertiesDataService")
public class ProtertiesDataServiceImpl implements ProtertiesDataService {

	@Autowired(required = false)
	@Qualifier("protertiesDataMapper")
	private ProtertiesDataMapper protertiesDataMapper;
	
	@Override
	public List<ProtertiesData> queryByDataKeyList(String key) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("key", key);
		return protertiesDataMapper.queryByDataKeyList(map);
	}

}
