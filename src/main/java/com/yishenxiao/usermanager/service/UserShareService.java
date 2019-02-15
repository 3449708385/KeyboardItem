package com.yishenxiao.usermanager.service;

import java.util.List;

import com.yishenxiao.usermanager.beans.UserShare;

public interface UserShareService {

	List<UserShare> queryByUserIdAndDataId(Long userId, String dataId);

	int insertOneData(UserShare userShare);

	List<UserShare> queryByDataId(String dataId);

	List<UserShare> queryByUserId(Long userId);

}
