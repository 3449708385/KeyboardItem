package com.yishenxiao.commons.dao;

import java.util.List;

import com.yishenxiao.commons.beans.NativeCode;

public interface NativeCodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NativeCode record);

    int insertSelective(NativeCode record);

    NativeCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NativeCode record);

    int updateByPrimaryKey(NativeCode record);

	List<NativeCode> queryByAllData();
}