package com.yishenxiao.usermanager.dao;

import java.util.List;
import java.util.Map;

import com.yishenxiao.usermanager.beans.UserSchedule;

public interface UserScheduleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserSchedule record);

    int insertSelective(UserSchedule record);

    UserSchedule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserSchedule record);

    int updateByPrimaryKey(UserSchedule record);

	List<UserSchedule> queryByUIdList(Map<String, Object> map);

	int updateArticleType(Map<String, Object> map);

	int updateUserLoginCount(Map<String, Object> map);

	int updateUserLoginCountSpecialEdition(Map<String, Object> map);

	int queryByAllDataCount();

	List<UserSchedule> queryPartData(Map<String, Object> map);

	int updateUserLoginState(Map<String, Object> map);

	int updateUserHead(Map<String, Object> map);

	int updateSubtractMolCount(Map<String, Object> map);

	int updateIncreaseMolCount(Map<String, Object> map);

	List<UserSchedule> queryByPypass(Map<String, Object> map);

	int updateUserAuthenticationState(Map<String, Object> map);

	List<UserSchedule> queryBypasswd(Map<String, Object> map);

	int setPyPwSetup(Map<String, Object> map);
}