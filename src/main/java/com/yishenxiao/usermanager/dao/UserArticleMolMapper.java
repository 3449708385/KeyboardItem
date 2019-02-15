package com.yishenxiao.usermanager.dao;

import java.util.List;
import java.util.Map;

import com.yishenxiao.usermanager.beans.UserArticleMol;

public interface UserArticleMolMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserArticleMol record);

    int insertSelective(UserArticleMol record);

    UserArticleMol selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserArticleMol record);

    int updateByPrimaryKey(UserArticleMol record);

	List<UserArticleMol> queryByUserId(Map<String, Object> map);

	int updateTotalAndToday(Map<String, Object> map);

	int queryByAllDataCount();

	List<UserArticleMol> queryPartData(Map<String, Object> map);

	int updateTodayMolCount(Map<String, Object> map);
}