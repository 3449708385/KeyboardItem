package com.yishenxiao.usermanager.service;

import java.util.List;

import com.yishenxiao.usermanager.beans.User;

public interface UserService {

	List<User> queryByPhone(String phone);

	/**
	 * info 更新用户数据的单个或多个字段
	 * @param string
	 * @return
	 */
	int updateUser(User user);
	
	/**
	 * info 包含密码
	 * @param string
	 * @return
	 */
	List<User> queryByPhoneAndPasswd(String phone);

	/**
	 * info 查询用户Id
	 * @param string
	 * @return
	 */
	List<User> queryByaccount(String userStrId);

	
	/**
	 * info 插入一个数据
	 * @param string
	 * @return
	 */
	int insertOneData(User user);

	/**
	 * info 根据userId更新用户邀请码次数
	 * @param 
	 * @return
	 */
	int updateUserInvitationCodeCount(Long userId);

	/**
	 * info 根据userId查询用户数据
	 * @param 
	 * @return
	 */
	List<User> queryByUserId(Long userId);
	/**
	 * info 根据userId更新用户昵称
	 * @param 
	 * @return
	 */
	int userUpdateNickName(String nickname, Long id);

	/**
	 * info 根据userId更新用户登陆状态
	 * @param 
	 * @return
	 */
	int updateLoginstate(Long userId, int state);
}
