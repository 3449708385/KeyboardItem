package com.yishenxiao.commons.dao;

import java.util.List;

import com.yishenxiao.commons.beans.ArticleType;

public interface ArticleTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleType record);

    int insertSelective(ArticleType record);

    ArticleType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleType record);

    int updateByPrimaryKey(ArticleType record);

	List<ArticleType> getarticleTypeData();

	List<ArticleType> queryBySelf();
}