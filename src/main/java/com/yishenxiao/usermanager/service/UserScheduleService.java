package com.yishenxiao.usermanager.service;

import java.util.List;

import com.yishenxiao.usermanager.beans.UserSchedule;

public interface UserScheduleService {

	List<UserSchedule> queryByUIdList(Long userId);

	int insertOneData(UserSchedule userSchedule);

	int updateArticleType(Long id, Long articleTypeId, Integer type);

	int updateUserLoginCount(Long userId);

	int queryByAllDataCount();

	List<UserSchedule> queryPartData(int startCount, int eventCounts);

	int updateUserLoginState(Long id, int state);

	int updateUserHead(String headicon, Long id);

	int updateSubtractMolCount(Long id, Double rewardMolCount);

	int updateIncreaseMolCount(Long id, Double extensionmonery);

	List<UserSchedule> queryByPypass(Long userid, String pypasswd);

	int updateUserAuthenticationState(Long id, int state);

	List<UserSchedule> queryBypasswd(Long userId);

	int setPyPwSetup(Long id, String hex);

}
