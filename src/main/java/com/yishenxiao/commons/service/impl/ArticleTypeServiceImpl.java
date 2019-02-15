package com.yishenxiao.commons.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yishenxiao.commons.beans.ArticleType;
import com.yishenxiao.commons.dao.ArticleTypeMapper;
import com.yishenxiao.commons.service.ArticleTypeService;

@Service("articleTypeService")
public class ArticleTypeServiceImpl implements ArticleTypeService {
	
	@Autowired(required = false)
	@Qualifier("articleTypeMapper")
	private ArticleTypeMapper articleTypeMapper;

	@Override
	public List<ArticleType> getarticleTypeData() {
		return articleTypeMapper.getarticleTypeData();
	}

	@Override
	public List<ArticleType> queryBySelf() {
		return articleTypeMapper.queryBySelf();
	}
}
