package com.yishenxiao.usermanager.dao;

import java.util.List;
import java.util.Map;

import com.yishenxiao.usermanager.beans.UserShare;

public interface UserShareMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserShare record);

    int insertSelective(UserShare record);

    UserShare selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserShare record);

    int updateByPrimaryKey(UserShare record);

	List<UserShare> queryByUserIdAndDataId(Map<String, Object> map);

	List<UserShare> queryByDataId(Map<String, Object> map);

	List<UserShare> queryByUserId(Map<String, Object> map);
}