package com.yishenxiao.commons.dao;

import java.util.List;
import java.util.Map;

import com.yishenxiao.commons.beans.MolHistoricalRecord;

public interface MolHistoricalRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MolHistoricalRecord record);

    int insertSelective(MolHistoricalRecord record);

    MolHistoricalRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MolHistoricalRecord record);

    int updateByPrimaryKey(MolHistoricalRecord record);

	List<MolHistoricalRecord> queryByUserId(Map<String, Object> map);
}