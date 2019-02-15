package com.yishenxiao.usermanager.service;

import java.util.List;

import com.yishenxiao.usermanager.beans.UserInvitationcode;

public interface UserInvitationCodeService {

	/**
	 * info 根据邀请码查询用户邀请表的那条数据
	 * @param invitationCode
	 * @return
	 */
	List<UserInvitationcode> queryByInvitationCodeList(String invitationCode);

	/**
	 * info 为用户生成对应的邀请码
	 * @param invitationCode
	 * @return
	 */
	String getInvitationCode(Long userId);
	/**
	 * info 根据userId得到用户的邀请码
	 * @param userId
	 * @return
	 */
	List<UserInvitationcode> queryByUserId(Long userId);

}
