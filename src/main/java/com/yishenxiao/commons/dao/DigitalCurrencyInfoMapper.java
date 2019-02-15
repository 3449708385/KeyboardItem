package com.yishenxiao.commons.dao;

import com.yishenxiao.commons.beans.DigitalCurrencyInfo;

public interface DigitalCurrencyInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DigitalCurrencyInfo record);

    int insertSelective(DigitalCurrencyInfo record);

    DigitalCurrencyInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DigitalCurrencyInfo record);

    int updateByPrimaryKey(DigitalCurrencyInfo record);
}