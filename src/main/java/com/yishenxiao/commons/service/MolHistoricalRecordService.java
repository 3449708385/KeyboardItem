package com.yishenxiao.commons.service;

import java.util.List;

import com.yishenxiao.commons.beans.MolHistoricalRecord;

public interface MolHistoricalRecordService {

	int insertOneData(MolHistoricalRecord molHistoricalRecord);

	List<MolHistoricalRecord> queryByUserId(Long userId);

}
