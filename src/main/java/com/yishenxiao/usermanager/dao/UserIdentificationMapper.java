package com.yishenxiao.usermanager.dao;

import java.util.List;
import java.util.Map;

import com.yishenxiao.usermanager.beans.UserIdentification;

public interface UserIdentificationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserIdentification record);

    int insertSelective(UserIdentification record);

    UserIdentification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserIdentification record);

    int updateByPrimaryKey(UserIdentification record);

	List<UserIdentification> queryByUserId(Map<String,Object> map);

	List<UserIdentification> queryByUserIdAndIdentification(Map<String, Object> map);
}