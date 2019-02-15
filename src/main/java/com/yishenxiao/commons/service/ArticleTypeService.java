package com.yishenxiao.commons.service;

import java.util.List;

import com.yishenxiao.commons.beans.ArticleType;

public interface ArticleTypeService {

	/**
	 * info 获取有效的文章分类
	 * @param 
	 */
	List<ArticleType> getarticleTypeData();

	List<ArticleType> queryBySelf();

}
