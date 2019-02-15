package com.yishenxiao.commons.service.impl.quzrtz;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.yishenxiao.commons.utils.DateUtils;
import com.yishenxiao.commons.utils.PropertiesUtils;
import com.yishenxiao.commons.utils.SpringContextUtils;
import com.yishenxiao.usermanager.beans.UserArticleMol;
import com.yishenxiao.usermanager.service.UserArticleMolService;

@DisallowConcurrentExecution
public class ResetTodayMolServiceImpl extends QuartzJobBean {

	private Logger logger = LoggerFactory.getLogger(ResetTodayMolServiceImpl.class);

	private UserArticleMolService userArticleMolService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		getBean();
		resetTodayMol();
	}

	public void getBean() {
		if (userArticleMolService == null) {
			userArticleMolService = (UserArticleMolService) SpringContextUtils.getBean("userArticleMolService");
		}
	}

	private void resetTodayMol() {
		int eventCounts = 1500;
		// 群成员
		int dataCounts = userArticleMolService.queryByAllDataCount();
		int bCount = dataCounts / eventCounts + 1;
		for (int m = 0; m < bCount; m++) {
			List<UserArticleMol> userArticleMolList = userArticleMolService.queryPartData(m * eventCounts, eventCounts);
			if (userArticleMolList.size() != 0) {
				int poolSize = Integer
						.parseInt(PropertiesUtils.getInfoConfigProperties().getProperty("myUserPhoneSMSCallSize"));
				if (userArticleMolList.size() < poolSize) {
					poolSize = userArticleMolList.size();
				}
				// 为线程分配数据
				int listSize = (int) Math.ceil((double) userArticleMolList.size() / poolSize);
				List<List<UserArticleMol>> listFP = new ArrayList<List<UserArticleMol>>(poolSize);
				for (int j = 0; j < poolSize; j++) {
					List<UserArticleMol> tempList = new ArrayList<UserArticleMol>();
					for (int i = j * listSize; i < (listSize + listSize * j); i++) {
						if (i < userArticleMolList.size()) {
							tempList.add(userArticleMolList.get(i));
						}
					}
					listFP.add(tempList);
				}
				ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
				List<ResetTodayMolMyCallable> myCallableExec = new ArrayList<ResetTodayMolMyCallable>();
				for (int i = 0; i < poolSize; i++) {
					myCallableExec.add(new ResetTodayMolMyCallable(listFP.get(i)));
				}
				try {
					executorService.invokeAll(myCallableExec);
				} catch (InterruptedException e) {
					logger.error("重置用户TodayMol count，预处理用户进程执行出现异常！");
				}

			} else {
				logger.error(DateUtils.getNowTime() + " ,重置用户TodayMol count完成！");
			}
		}
	}
}
