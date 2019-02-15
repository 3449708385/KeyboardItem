package com.yishenxiao.commons.service;

import java.util.List;

import com.yishenxiao.commons.beans.PhoneSMS;

public interface PhoneSMSService {

	/**
	 * info 根据手机号查询用户发送短信记录
	 * @param phoneSMS
	 */
	List<PhoneSMS> queryByPhone(String phone);

	/**
	 * info 这个接口会更新数据，并使短信计数+1
	 * @param phoneSMS
	 */
	int updateByCountAndData(PhoneSMS phoneSMS);

	int insertData(PhoneSMS phoneSMS);
	
	/**
	 * info 查询全部手机号发送短信记录
	 * @param phoneSMS
	 */
	/*List<PhoneSMS> queryByAllPhoneSMSData();*/
    
	/**
	 * info 查询全部数据条数
	 * @param phoneSMS
	 */
	int queryByAllDataCount();

	/**
	 * info 查询部分手机号发送短信记录
	 * @param phoneSMS
	 */
	List<PhoneSMS> queryPartData(int startId, int eventCounts);

	int unLockPhoneSMS(Long id, int count);

}
