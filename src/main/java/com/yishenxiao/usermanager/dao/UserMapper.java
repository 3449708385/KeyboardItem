package com.yishenxiao.usermanager.dao;

import java.util.List;
import java.util.Map;

import com.yishenxiao.usermanager.beans.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	List<User> queryByPhone(Map<String, Object> map);

	List<User> queryByPhoneAndPasswd(Map<String, Object> map);

	List<User> queryByaccount(Map<String, Object> map);

	int updateUserInvitationCodeCount(Map<String, Object> map);

	List<User> queryByUserId(Map<String, Object> map);

	int userUpdateNickName(Map<String, Object> map);

	int updateLoginstate(Map<String, Object> map);
}