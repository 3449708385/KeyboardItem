package com.yishenxiao.usermanager.dao;

import java.util.List;
import java.util.Map;

import com.yishenxiao.usermanager.beans.UserInvitationcode;

public interface UserInvitationcodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInvitationcode record);

    int insertSelective(UserInvitationcode record);

    UserInvitationcode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInvitationcode record);

    int updateByPrimaryKey(UserInvitationcode record);

	List<UserInvitationcode> queryByInvitationCodeList(Map<String, Object> map);

	List<UserInvitationcode> queryByUserId(Map<String, Object> map);
}