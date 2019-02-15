package com.yishenxiao.usermanager.service;

import java.util.List;

import com.yishenxiao.usermanager.beans.UserIdentification;

public interface UserIdentificationService {
    /**
     * @info 根据userId查询实名认证信息
     * @param userId
     * @return
     */
	List<UserIdentification> queryByUserId(Long userId);

	int insertOneData(UserIdentification userIdentification);

	List<UserIdentification> queryByUserIdAndIdentification(Long userId, String identificationCard);

}
