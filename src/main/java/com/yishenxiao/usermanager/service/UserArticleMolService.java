package com.yishenxiao.usermanager.service;

import java.util.List;

import com.yishenxiao.usermanager.beans.UserArticleMol;

public interface UserArticleMolService {

	List<UserArticleMol> queryByUserId(Long userId);

	int insertOneData(UserArticleMol userArticleMol);

	int updateTotalAndToday(Long id, Double dataCount);

	int queryByAllDataCount();

	List<UserArticleMol> queryPartData(int start, int eventCounts);

	int updateTodayMolCount(Long id, Double molCount);

}
