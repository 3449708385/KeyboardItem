package com.yishenxiao.commons.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yishenxiao.commons.beans.NativeCode;
import com.yishenxiao.commons.dao.NativeCodeMapper;
import com.yishenxiao.commons.service.NativeCodeService;

@Service("nativeCodeService")
public class NativeCodeServiceImpl implements NativeCodeService {
	
	@Autowired(required = false)
	@Qualifier("nativeCodeMapper")
	private NativeCodeMapper nativeCodeMapper;

	@Override
	public List<NativeCode> queryByAllData() {		
		return nativeCodeMapper.queryByAllData();
	}

}
