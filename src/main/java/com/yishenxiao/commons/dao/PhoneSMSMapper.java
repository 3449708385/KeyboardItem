package com.yishenxiao.commons.dao;

import java.util.List;
import java.util.Map;

import com.yishenxiao.commons.beans.PhoneSMS;

public interface PhoneSMSMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PhoneSMS record);

    int insertSelective(PhoneSMS record);

    PhoneSMS selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PhoneSMS record);

    int updateByPrimaryKeyWithBLOBs(PhoneSMS record);

    int updateByPrimaryKey(PhoneSMS record);

	List<PhoneSMS> queryByPhone(Map<String,Object> map);

	int updateByCountAndData(PhoneSMS phoneSMS);

	int queryByAllDataCount();

	List<PhoneSMS> queryPartData(Map<String, Object> map);

	int unLockPhoneSMS(Map<String, Object> map);
}